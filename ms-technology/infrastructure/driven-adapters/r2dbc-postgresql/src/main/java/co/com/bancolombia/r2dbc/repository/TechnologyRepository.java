package co.com.bancolombia.r2dbc.repository;

import co.com.bancolombia.r2dbc.model.RequestDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface RequestDBRepository extends ReactiveCrudRepository<RequestDTO, UUID>{

    Flux<RequestDTO> findByIdMassive(UUID idMassive);

}
