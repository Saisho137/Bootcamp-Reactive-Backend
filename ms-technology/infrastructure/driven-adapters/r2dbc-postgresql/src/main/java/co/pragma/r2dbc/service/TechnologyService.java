package co.pragma.r2dbc.service;


import co.pragma.logic.TechnologyGateway;
import co.pragma.model.entity.Technology;
import co.pragma.r2dbc.mapper.TechnologyMapper;
import co.pragma.r2dbc.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TechnologyService implements TechnologyGateway {
    private final TechnologyRepository technologyRepository;
    private final TechnologyMapper technologyMapper;

    @Override
    public Mono<Technology> getTechnologyByName(String name) {
        return technologyRepository.findByName(name).map(technologyMapper::toEntity);
    }

    @Override
    public Mono<Technology> saveTechnology(Technology technology) {
        return technologyRepository.save(technologyMapper.toDto(technology)).map(technologyMapper::toEntity);
    }

    @Override
    public Flux<Technology> getAllTechnologies() {
        return technologyRepository.findAll().map(technologyMapper::toEntity);
    }

    @Override
    public Mono<Technology> getTechnologyById(Long id) {
        return technologyRepository.findById(id).map(technologyMapper::toEntity);
    }

    @Override
    public Mono<Void> deleteTechnologyById(Long id) {
        return technologyRepository.deleteById(id);
    }
}
