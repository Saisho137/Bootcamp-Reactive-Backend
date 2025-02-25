package co.pragma.usecase;

import co.pragma.model.capacity.Capacity;
import co.pragma.model.capacity.gateway.CapacityGateway;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CapacityUseCase {
    private final CapacityGateway capacityGateway;

    public CapacityUseCase(CapacityGateway capacityGateway) {
        this.capacityGateway = capacityGateway;
    }

    public Flux<Capacity> getAllCapacity(int page, int size, String sort) {
        return capacityGateway.getAllCapacity(page, size, sort);
    }

    public Flux<Capacity> getAllCapacityByIds(List<Long> ids) {
        return capacityGateway.getAllCapacityByIds(ids);
    }

    public Mono<Capacity> saveCapacity(Capacity capacity) {
        return capacityGateway.saveCapacity(capacity);
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
