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
        return Flux.fromIterable(bootcamp.getCapacitiesIds())
                .distinct()
                .collectList()
                .flatMap(uniqueIds ->
                        validateCapacities(uniqueIds, bootcamp.getCapacitiesIds())
                                .then(capacityClientPort.confirmCapacities(new CapacityIdList(uniqueIds)))
                                .flatMap(confirmed -> Boolean.TRUE.equals(confirmed) ?
                                        bootcampPersistencePort.getBootcampByName(bootcamp.getName())
                                                .flatMap(existing -> updateExistingBootcamp(existing, uniqueIds))
                                                .switchIfEmpty(createNewBootcamp(bootcamp, uniqueIds))
                                        : Mono.error(new CapacityNotFoundException(BootcampErrorMessage.CAPACITIES_NOT_FOUND.getMessage()))
                                )
                );
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

    private Mono<Void> validateCapacities(List<Long> uniqueIds, List<Long> originalIds) {
        return Mono.just(uniqueIds)
                .filter(ids -> ids.size() == originalIds.size())
                .switchIfEmpty(Mono.error(new CapacityDuplicationException(BootcampErrorMessage.CAPACITIES_DUPLICATED.getMessage())))
                .filter(ids -> !ids.isEmpty() && ids.size() <= 4)
                .switchIfEmpty(Mono.error(new CapacityAmountException(BootcampErrorMessage.CAPACITIES_AMOUNT_INVALID.getMessage())))
                .then();
    }

    private Mono<Bootcamp> updateExistingBootcamp(Bootcamp existingBootcamp, List<Long> uniqueIds) {
        return Mono.just(uniqueIds)
                .map(ids -> Stream.concat(
                        existingBootcamp.getCapacitiesIds().stream(),
                        ids.stream()
                ).distinct().toList())
                .filter(updatedCapacities -> updatedCapacities.size() <= 4)
                .switchIfEmpty(Mono.error(new CapacityAmountException(BootcampErrorMessage.CAPACITIES_EXCEED_LIMIT.getMessage())))
                .filter(updatedCapacities -> !updatedCapacities.equals(existingBootcamp.getCapacitiesIds()))
                .switchIfEmpty(Mono.error(new CapacityAlreadyAssociatedException(BootcampErrorMessage.CAPACITIES_ALREADY_ASSOCIATED.getMessage())))
                .flatMap(updatedCapacities -> {
                    existingBootcamp.setCapacitiesIds(updatedCapacities);
                    existingBootcamp.setCapacitiesCount(updatedCapacities.size());
                    return bootcampPersistencePort.saveBootcamp(existingBootcamp);
                });
    }

    private Mono<Bootcamp> createNewBootcamp(BootcampRequest bootcamp, List<Long> uniqueIds) {
        return bootcampPersistencePort.saveBootcamp(
                Bootcamp.builder()
                        .name(bootcamp.getName())
                        .description(bootcamp.getDescription())
                        .capacitiesCount(uniqueIds.size())
                        .capacitiesIds(uniqueIds)
                        .build());
    }

}
