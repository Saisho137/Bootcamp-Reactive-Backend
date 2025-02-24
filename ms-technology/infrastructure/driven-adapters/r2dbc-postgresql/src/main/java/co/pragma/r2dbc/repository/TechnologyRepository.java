package co.pragma.r2dbc.repository;

import co.pragma.r2dbc.dto.TechnologyDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TechnologyRepository extends ReactiveCrudRepository<TechnologyDTO, Long> {
    @Query("SELECT * FROM ms_technology.technologies WHERE name ILIKE :name")
    Mono<TechnologyDTO> findByName(String name);

    Flux<TechnologyDTO> findAllBy(PageRequest pageRequest);
}
