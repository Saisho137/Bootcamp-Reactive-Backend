package co.pragma.ms_bootcamp.application.usecase;

import co.pragma.ms_bootcamp.application.dto.BootcampRequest;
import co.pragma.ms_bootcamp.application.dto.CapacityIdList;
import co.pragma.ms_bootcamp.domain.enums.BootcampErrorMessage;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityAlreadyAssociatedException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityAmountException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityDuplicationException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityNotFoundException;
import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.domain.model.BootcampWithChildren;
import co.pragma.ms_bootcamp.domain.port.input.BootcampPort;
import co.pragma.ms_bootcamp.domain.port.output.BootcampPersistencePort;
import co.pragma.ms_bootcamp.domain.port.output.CapacityClientPort;
import reactor.core.publisher.Flux;
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
            return Mono.error(
                    new CapacityDuplicationException(BootcampErrorMessage.CAPACITIES_DUPLICATED.getMessage())
            );
        }

        if (uniqueIds.isEmpty() || uniqueIds.size() > 4) {
            return Mono.error(
                    new CapacityAmountException(BootcampErrorMessage.CAPACITIES_AMOUNT_INVALID.getMessage())
            );
        }

        return capacityClientPort.confirmCapacities(
                        CapacityIdList.builder().ids(uniqueIds).build())
                .flatMap(confirmed -> {
                    if (Boolean.FALSE.equals(confirmed)) {
                        return Mono.error(
                                new CapacityNotFoundException(BootcampErrorMessage.CAPACITIES_NOT_FOUND.getMessage())
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
                                            new CapacityAmountException(BootcampErrorMessage.CAPACITIES_EXCEED_LIMIT.getMessage())
                                    );
                                }

                                if (updatedCapacities.equals(existingBootcamp.getCapacitiesIds())) {
                                    return Mono.error(
                                            new CapacityAlreadyAssociatedException(BootcampErrorMessage.CAPACITIES_ALREADY_ASSOCIATED.getMessage())
                                    );
                                }

                                existingBootcamp.setCapacitiesIds(updatedCapacities);
                                existingBootcamp.setCapacitiesCount(updatedCapacities.size());
                                return bootcampPersistencePort.saveBootcamp(existingBootcamp);

                            }).switchIfEmpty(
                                    bootcampPersistencePort.saveBootcamp(Bootcamp.builder()
                                            .name(bootcamp.getName())
                                            .description(bootcamp.getDescription())
                                            .capacitiesCount(uniqueIds.size())
                                            .capacitiesIds(uniqueIds)
                                            .build())
                            );
                });
    }

    @Override
    public Flux<BootcampWithChildren> getAllBootcamps(int page, int size, String sort, String sortBy) {
        return bootcampPersistencePort.getAllBootcamps(page, size, sort, sortBy)
                .flatMapSequential(bootcamp ->
                        capacityClientPort.getCapacitiesAndTechnologies(bootcamp.getCapacitiesIds())
                                .collectList()
                                .map(capacitiesList -> BootcampWithChildren.builder()
                                        .bootcamp(bootcamp)
                                        .capacities(capacitiesList)
                                        .build()
                                )
                );
    }

    @Override
    public Mono<Long> countAllBootcamps() {
        return bootcampPersistencePort.countAllBootcamps();
    }

}
