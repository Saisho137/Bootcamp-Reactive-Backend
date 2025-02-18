package co.pragma.logic;

import co.pragma.model.entity.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TechnologyGateway {
    Mono<Technology> getTechnologyByName(String name);

    Mono<Technology> saveTechnology(Technology technology);

    Flux<Technology> getAllTechnologies();

    Mono<Technology> getTechnologyById(Long id);

    Mono<Void> deleteTechnologyById(Long id);
}
