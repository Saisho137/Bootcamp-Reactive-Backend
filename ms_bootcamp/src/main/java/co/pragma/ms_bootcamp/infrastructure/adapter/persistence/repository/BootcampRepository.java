package co.pragma.ms_bootcamp.infrastructure.adapter.persistence.repository;

import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.entity.BootcampEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BootcampRepository extends ReactiveMongoRepository<BootcampEntity, String> {
    Flux<BootcampEntity> findAllBy(PageRequest pageable);
}
