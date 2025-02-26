package co.pragma.model.technology_capacity.gateway;

import co.pragma.model.technology_capacity.TechnologyIds;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyCapacityGateway {
    Mono<Boolean> confirmTechnologies(TechnologyIds technologyIds);
    Mono<Void> saveTechnologiesFromCapacity(Long capacityId, List<Long> technologyIds);
    Flux<Long> getTechnologiesByCapacityId(Long capacityId);
}
