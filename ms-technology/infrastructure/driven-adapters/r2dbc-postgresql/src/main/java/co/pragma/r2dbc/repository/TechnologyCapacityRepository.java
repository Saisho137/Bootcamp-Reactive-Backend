package co.pragma.r2dbc.repository;

import co.pragma.r2dbc.dto.TechnologyCapacityDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TechnologyCapacityRepository extends ReactiveCrudRepository<TechnologyCapacityDTO, Long> {
    Flux<TechnologyCapacityDTO> findByCapacityId(Long capacityId);
}
