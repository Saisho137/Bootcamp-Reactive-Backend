package co.pragma.r2dbc.service;

import co.pragma.model.capacity.Capacity;
import co.pragma.model.capacity.gateway.CapacityGateway;
import co.pragma.r2dbc.mapper.CapacityMapperI;
import co.pragma.r2dbc.repository.CapacityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CapacityService implements CapacityGateway {
    private final CapacityRepository capacityRepository;
    private final CapacityMapperI capacityMapperI;

    @Override
    public Flux<Capacity> getAllCapacityByIds(List<Long> ids) {
        return capacityRepository.findAllById(ids).map(capacityMapperI::toEntity);
    }

    @Override
    public Flux<Capacity> getAllCapacity(int page, int size, String sort) {
        Sort.Direction direction = sort.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, "name"));

        return capacityRepository.findAllBy(pageRequest).map(capacityMapperI::toEntity);
    }

    @Override
    public Flux<Capacity> getAllCapacityWithTechnologies(int page, int size, String sort, String sortBy) {
        Sort.Direction direction = sort.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        String sortByField = sortBy.equals("name") ? "name" : "technologyCount";
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortByField));

        return capacityRepository.findAllBy(pageRequest).map(capacityMapperI::toEntity);
    }

    @Override
    public Mono<Capacity> getCapacityByName(String name) {
        return capacityRepository.findByName(name).map(capacityMapperI::toEntity);
    }

    @Override
    public Mono<Capacity> saveCapacity(Capacity capacity) {
        return capacityRepository.save(capacityMapperI.toDto(capacity)).map(capacityMapperI::toEntity);
    }

    @Override
    public Mono<Long> countAllCapacity() {
        return capacityRepository.count();
    }

    @Override
    public Mono<Capacity> getCapacityById(Long id) {
        return capacityRepository.findById(id).map(capacityMapperI::toEntity);
    }

    @Override
    public Mono<Void> deleteCapacityById(Long id) {
        return capacityRepository.deleteById(id);
    }
}
