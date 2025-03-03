package co.pragma.ms_bootcamp.application.usecase;

import co.pragma.ms_bootcamp.application.dto.BootcampRequest;
import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.domain.port.input.BootcampPort;
import co.pragma.ms_bootcamp.domain.port.output.BootcampPersistencePort;
import reactor.core.publisher.Mono;

public class BootcampUseCase implements BootcampPort {
    private final BootcampPersistencePort bootcampPersistencePort;

    public BootcampUseCase(BootcampPersistencePort bootcampPersistencePort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
    }

    @Override
    public Mono<Bootcamp> saveBootcamp(BootcampRequest bootcamp) {
        return bootcampPersistencePort.saveBootcamp(Bootcamp.builder()
                .name(bootcamp.getName())
                .description(bootcamp.getDescription())
                .capacitiesIds(bootcamp.getCapacitiesIds())
                .build());
    }
}
