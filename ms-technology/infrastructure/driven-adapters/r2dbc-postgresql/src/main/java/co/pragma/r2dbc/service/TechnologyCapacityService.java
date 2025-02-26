package co.pragma.r2dbc.service;

import co.pragma.logic.TechnologyCapacityGateway;
import co.pragma.model.entity.TechnologyCapacity;
import co.pragma.r2dbc.dto.TechnologyCapacityDTO;
import co.pragma.r2dbc.mapper.TechnologyCapacityMapper;
import co.pragma.r2dbc.repository.TechnologyCapacityRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class TechnologyCapacityService implements TechnologyCapacityGateway {
    private final TechnologyCapacityRepository technologyCapacityRepository;
    private final TechnologyCapacityMapper technologyCapacityMapper;

    @Override
    public Mono<TechnologyCapacity> save(TechnologyCapacity technologyCapacity) {
        return technologyCapacityRepository.save(technologyCapacityMapper.toDto(technologyCapacity))
                .map(technologyCapacityMapper::toEntity);
    }

    @Override
    public Flux<TechnologyCapacity> saveAll(List<TechnologyCapacity> technologyCapacities) {
        return technologyCapacityRepository.saveAll(
                technologyCapacities.stream()
                        .map(technologyCapacityMapper::toDto)
                        .toList()
        ).map(technologyCapacityMapper::toEntity);
    }

    @Override
    public Flux<TechnologyCapacity> getByCapacityId(Long capacityId) {
        return technologyCapacityRepository.findByCapacityId(capacityId).map(technologyCapacityMapper::toEntity);
    }

    @Override
    public Flux<Long> getTechnologiesByCapacityId(Long capacityId) {
        return technologyCapacityRepository.findByCapacityId(capacityId).map(TechnologyCapacityDTO::getTechnologyId);
    }

}
