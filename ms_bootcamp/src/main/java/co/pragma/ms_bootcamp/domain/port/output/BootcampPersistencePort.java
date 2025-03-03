package co.pragma.ms_bootcamp.domain.port.output;

import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcampPersistencePort {
    Mono<Bootcamp> saveBootcamp(Bootcamp bootcamp);

    Mono<Bootcamp> getBootcampByName(String name);

    Flux<Bootcamp> getAllBootcamps(int page, int size, String sort, String sortBy);

    Mono<Long> countAllBootcamps();
}
