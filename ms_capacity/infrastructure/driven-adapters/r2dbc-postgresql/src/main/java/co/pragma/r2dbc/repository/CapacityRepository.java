package co.pragma.r2dbc.repository;

import co.pragma.r2dbc.dto.CapacityDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapacityRepository extends ReactiveCrudRepository<CapacityDTO, Long> {
    Flux<CapacityDTO> findAllBy(PageRequest pageRequest);
    Mono<CapacityDTO> findByName(String name);
}
