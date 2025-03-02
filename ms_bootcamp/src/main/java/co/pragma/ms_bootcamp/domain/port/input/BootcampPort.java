package co.pragma.ms_bootcamp.domain.port.input;

import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import reactor.core.publisher.Mono;

public interface BootcampPort {
    Mono<Void> saveBootcamp(Bootcamp bootcamp);
}
