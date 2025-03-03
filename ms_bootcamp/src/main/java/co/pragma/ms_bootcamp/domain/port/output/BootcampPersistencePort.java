package co.pragma.ms_bootcamp.domain.port.output;

import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import reactor.core.publisher.Mono;

public interface BootcampPersistencePort {
    Mono<Bootcamp> saveBootcamp(Bootcamp bootcamp);

    Mono<Bootcamp> getBootcampByName(String name);
}
