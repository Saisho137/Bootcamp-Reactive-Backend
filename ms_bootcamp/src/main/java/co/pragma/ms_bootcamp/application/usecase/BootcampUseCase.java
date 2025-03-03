package co.pragma.ms_bootcamp.application.usecase;

import co.pragma.ms_bootcamp.application.dto.BootcampRequest;
import co.pragma.ms_bootcamp.application.dto.CapacityIdList;
import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.domain.port.input.BootcampPort;
import co.pragma.ms_bootcamp.domain.port.output.BootcampPersistencePort;
import co.pragma.ms_bootcamp.domain.port.output.CapacityClientPort;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

public class BootcampUseCase implements BootcampPort {
    private final BootcampPersistencePort bootcampPersistencePort;
    private final CapacityClientPort capacityClientPort;

    public BootcampUseCase(BootcampPersistencePort bootcampPersistencePort, CapacityClientPort capacityClientPort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
        this.capacityClientPort = capacityClientPort;
    }

    @Override
    public Mono<Bootcamp> saveBootcamp(BootcampRequest bootcamp) {
        List<Long> uniqueIds = bootcamp.getCapacitiesIds().stream().distinct().toList();

        if (uniqueIds.size() != bootcamp.getCapacitiesIds().size()) {
            return Mono.error(new RuntimeException("Los ids de Capacidades no pueden repetirse"));
        }

        if (uniqueIds.isEmpty() || uniqueIds.size() > 4) {
            return Mono.error(new RuntimeException("El número de Capacidades debe ser entre 1 y 4"));
        }

        return capacityClientPort.confirmCapacities(
                        CapacityIdList.builder().ids(uniqueIds).build())
                .flatMap(confirmed -> {
                    if (Boolean.FALSE.equals(confirmed)) {
                        return Mono.error(
                                new RuntimeException("No se pueden guardar los datos, 1 o varios IDs de capacities no existen")
                        );
                    }

                    return bootcampPersistencePort.getBootcampByName(bootcamp.getName())
                            .flatMap(existingBootcamp -> {
                                List<Long> updatedCapacities = Stream.concat(
                                        existingBootcamp.getCapacitiesIds().stream(),
                                        uniqueIds.stream()
                                ).distinct().toList();

                                if (updatedCapacities.size() > 4) {
                                    return Mono.error(
                                            new RuntimeException("El número de capacidades asociadas no puede superar 4")
                                    );
                                }

                                if (updatedCapacities.equals(existingBootcamp.getCapacitiesIds())) {
                                    return Mono.error(
                                            new RuntimeException("Todas las capacidades enviadas ya estaban asociadas al Bootcamp!")
                                    );
                                }

                                existingBootcamp.setCapacitiesIds(updatedCapacities);
                                return bootcampPersistencePort.saveBootcamp(existingBootcamp);

                            }).switchIfEmpty(
                                    bootcampPersistencePort.saveBootcamp(Bootcamp.builder()
                                            .name(bootcamp.getName())
                                            .description(bootcamp.getDescription())
                                            .capacitiesIds(bootcamp.getCapacitiesIds())
                                            .build())
                            );
                });
    }
}
