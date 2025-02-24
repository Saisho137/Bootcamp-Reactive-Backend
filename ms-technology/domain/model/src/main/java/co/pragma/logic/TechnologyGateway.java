package co.pragma.logic;

import co.pragma.model.entity.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyGateway {
    Flux<Technology> getAllTechnologiesByIds(List<Long> ids);

    Mono<Technology> getTechnologyByName(String name);

    Mono<Technology> saveTechnology(Technology technology);

    Flux<Technology> getAllTechnologies(int page, int size,String sort);

    Mono<Long> countAllTechnologies();

    Mono<Technology> getTechnologyById(Long id);

    Mono<Void> deleteTechnologyById(Long id);
}
