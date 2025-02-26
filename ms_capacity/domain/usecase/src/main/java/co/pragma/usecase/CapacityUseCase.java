package co.pragma.usecase;

import co.pragma.exceptions.TechnologiesNotFoundException;
import co.pragma.model.capacity.Capacity;
import co.pragma.model.capacity.CapacityRequest;
import co.pragma.model.capacity.gateway.CapacityGateway;
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

    public Flux<Capacity> getAllCapacityByIds(List<Long> ids) {
        return capacityGateway.getAllCapacityByIds(ids);
    }

    public Mono<Capacity> saveCapacity(CapacityRequest capacity) {
        return technologyCapacityGateway.confirmTechnologies(
                        TechnologyIds.builder().ids(capacity.getTechnologyIds()).build())
                .flatMap(confirmed -> {
                    if (!confirmed) {
                        return Mono.error(new TechnologiesNotFoundException(
                                "No se pueden guardar los datos, 1 o varios IDs de tecnología no existen"
                        ));
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
                                                return Mono.error(error);
                                            })
                            );
                });
    }

    public Mono<Capacity> getCapacityByName(String name) {
        return capacityGateway.getCapacityByName(name);
    }

    public Mono<Long> countAllCapacity() {
        return capacityGateway.countAllCapacity();
    }

    public Mono<Capacity> getCapacityById(Long id) {
        return capacityGateway.getCapacityById(id);
    }

    public Mono<Void> deleteCapacityById(Long id) {
        return capacityGateway.deleteCapacityById(id);
    }
}
