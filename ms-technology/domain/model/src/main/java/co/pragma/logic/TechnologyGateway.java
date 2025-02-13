package co.pragma.logic;

import co.pragma.model.entity.Technology;
import reactor.core.publisher.Mono;

public interface TechnologyGateway {
    Mono<Technology> getTechnologyByName(String name);
}
