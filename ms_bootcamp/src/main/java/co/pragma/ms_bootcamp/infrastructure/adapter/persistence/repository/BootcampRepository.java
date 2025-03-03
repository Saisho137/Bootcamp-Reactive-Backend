package co.pragma.ms_bootcamp.infrastructure.adapter.persistence.repository;

import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.entity.BootcampEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcampRepository extends ReactiveMongoRepository<BootcampEntity, String> {
    Mono<BootcampEntity> findByName(String name);

    Flux<BootcampEntity> findAllBy(PageRequest pageable);
}
