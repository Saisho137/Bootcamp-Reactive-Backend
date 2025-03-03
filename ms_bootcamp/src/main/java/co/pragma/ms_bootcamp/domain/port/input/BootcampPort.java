package co.pragma.ms_bootcamp.domain.port.input;

import co.pragma.ms_bootcamp.application.dto.BootcampRequest;
import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.domain.model.BootcampWithChildren;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcampPort {
    Mono<Bootcamp> saveBootcamp(BootcampRequest request);
    Flux<BootcampWithChildren> getAllBootcamps(int page, int size, String sort, String sortBy);
    Mono<Long> countAllBootcamps();
}
