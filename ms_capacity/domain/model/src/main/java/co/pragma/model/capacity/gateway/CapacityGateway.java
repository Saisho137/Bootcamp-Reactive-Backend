package co.pragma.model.capacity.gateway;

import co.pragma.model.capacity.Capacity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapacityGateway {
    Flux<Capacity> getAllCapacityByIds(List<Long> ids);

    Flux<Capacity> getAllCapacity(int page, int size, String sort);

    Flux<Capacity> getAllCapacityWithTechnologies(int page, int size, String sort, String sortBy);

    Mono<Capacity> getCapacityByName(String name);

    Mono<Capacity> saveCapacity(Capacity capacity);

    Mono<Long> countAllCapacity();

    Mono<Boolean> confirmCapacities(List<Long> capacityIds);

    Mono<Capacity> getCapacityById(Long id);

    Mono<Void> deleteCapacityById(Long id);
}
