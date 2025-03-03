package co.pragma.usecase;

import co.pragma.exceptions.CapacityAlreadyHaveTheseTechnologiesException;
import co.pragma.exceptions.TechnologiesAmountException;
import co.pragma.exceptions.TechnologiesNotFoundException;
import co.pragma.model.capacity.Capacity;
import co.pragma.model.capacity.CapacityRequest;
import co.pragma.model.capacity.gateway.CapacityGateway;
import co.pragma.model.technology_capacity.CapacityIds;
import co.pragma.model.technology_capacity.CapacityWithTechnologies;
import co.pragma.model.technology_capacity.TechnologyIds;
import co.pragma.model.technology_capacity.gateway.TechnologyCapacityGateway;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CapacityUseCase {
    private final CapacityGateway capacityGateway;
    private final TechnologyCapacityGateway technologyCapacityGateway;

    public CapacityUseCase(CapacityGateway capacityGateway, TechnologyCapacityGateway technologyCapacityGateway) {
        this.capacityGateway = capacityGateway;
        this.technologyCapacityGateway = technologyCapacityGateway;
    }

    public Flux<Capacity> getAllCapacity(int page, int size, String sort) {
        return capacityGateway.getAllCapacity(page, size, sort);
    }

    public Flux<CapacityWithTechnologies> getAllCapacityWithTechnologies(int page, int size, String sort, String sortBy) {
        return capacityGateway.getAllCapacityWithTechnologies(page, size, sort, sortBy)
                .flatMap(pagedResponse ->
                        technologyCapacityGateway.getTechnologiesByCapacityId(pagedResponse.getId()).collectList()
                                .map(technologies -> CapacityWithTechnologies.builder()
                                        .capacity(Capacity.builder()
                                                .id(pagedResponse.getId())
                                                .name(pagedResponse.getName())
                                                .description(pagedResponse.getDescription())
                                                .technologyCount(pagedResponse.getTechnologyCount())
                                                .build())
                                        .technologies(technologies)
                                        .build()
                                )
                );
    }

    public Flux<CapacityWithTechnologies> getAllCapacityWithTechnologiesByIds(List<Long> ids) {
        return capacityGateway.getAllCapacityByIds(ids)
                .flatMap(capacity -> technologyCapacityGateway.getTechnologiesByCapacityId(capacity.getId())
                        .collectList()
                        .map(technologies -> CapacityWithTechnologies.builder()
                                .capacity(capacity)
                                .technologies(technologies)
                                .build()
                        )
                );
    }

    public Mono<Capacity> saveCapacity(CapacityRequest capacity) {
        List<Long> uniqueTechnologyIds = capacity.getTechnologyIds().stream()
                .distinct()
                .toList();

        if (uniqueTechnologyIds.size() < 3 || uniqueTechnologyIds.size() > 20) {
            return Mono.error(new TechnologiesAmountException("Deben haber entre 3 y 20 tecnologías."));
        }

        return technologyCapacityGateway.confirmTechnologies(
                        TechnologyIds.builder().ids(capacity.getTechnologyIds()).build())
                .flatMap(confirmed -> {
                    if (Boolean.FALSE.equals(confirmed)) {
                        return Mono.error(new TechnologiesNotFoundException(
                                "No se pueden guardar los datos, 1 o varios IDs de tecnología no existen"
                        ));
                    }

                    return capacityGateway.getCapacityByName(capacity.getName())
                            .flatMap(existingCapacity -> {
                                if (existingCapacity != null) {
                                    return technologyCapacityGateway.saveTechnologiesFromCapacity(
                                                    existingCapacity.getId(),
                                                    capacity.getTechnologyIds())
                                            .thenReturn(existingCapacity)
                                            .onErrorResume(error ->
                                            {
                                                capacityGateway.deleteCapacityById(existingCapacity.getId());
                                                return Mono.error(
                                                        new CapacityAlreadyHaveTheseTechnologiesException(
                                                                "No se pueden guardar los datos, ya se han agregado las tecnologías seleccionadas."
                                                        ));
                                            });
                                }

                                return capacityGateway.saveCapacity(Capacity.builder()
                                                .name(capacity.getName())
                                                .description(capacity.getDescription())
                                                .technologyCount(capacity.getTechnologyIds().size())
                                                .build())
                                        .flatMap(savedCapacity ->
                                                technologyCapacityGateway.saveTechnologiesFromCapacity(
                                                                savedCapacity.getId(),
                                                                capacity.getTechnologyIds())
                                                        .thenReturn(savedCapacity)
                                                        .onErrorResume(error ->
                                                        {
                                                            capacityGateway.deleteCapacityById(savedCapacity.getId());
                                                            return Mono.error(
                                                                    new CapacityAlreadyHaveTheseTechnologiesException(
                                                                            "No se pueden guardar los datos, ya se han agregado las tecnologías seleccionadas."
                                                                    ));
                                                        })
                                        );
                            });
                });
    }

    public Mono<Capacity> getCapacityByName(String name) {
        return capacityGateway.getCapacityByName(name);
    }

    public Mono<Long> countAllCapacity() {
        return capacityGateway.countAllCapacity();
    }

    public Mono<Boolean> confirmCapacities(CapacityIds capacityIds) {
        return capacityGateway.confirmCapacities(capacityIds.getIds());
    }

    public Mono<Void> deleteCapacityById(Long id) {
        return capacityGateway.deleteCapacityById(id);
    }
}
