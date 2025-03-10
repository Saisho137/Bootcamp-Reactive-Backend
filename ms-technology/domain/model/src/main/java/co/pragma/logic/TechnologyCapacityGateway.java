package co.pragma.logic;

import co.pragma.model.entity.TechnologyCapacity;
import co.pragma.model.entity.TechnologyIdName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyCapacityGateway {
    Mono<TechnologyCapacity> save(TechnologyCapacity technologyCapacity);

    Flux<TechnologyCapacity> saveAll(List<TechnologyCapacity> technologyCapacities);

    Flux<TechnologyCapacity> getByCapacityId(Long capacityId);

    Flux<TechnologyIdName> getTechnologiesByCapacityId(Long capacityId);
}
