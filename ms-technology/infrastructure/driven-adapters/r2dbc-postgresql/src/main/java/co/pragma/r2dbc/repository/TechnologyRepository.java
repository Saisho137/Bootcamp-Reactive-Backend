package co.pragma.r2dbc.repository;

import co.pragma.r2dbc.dto.TechnologyDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TechnologyRepository extends ReactiveCrudRepository<TechnologyDTO, Long> {
    Mono<TechnologyDTO> findByName(String name);
}
