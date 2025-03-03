package co.pragma.ms_bootcamp.infrastructure.adapter.persistence.adapter;

import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.domain.port.output.BootcampPersistencePort;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.mapper.BootcampMapperI;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampPersistenceAdapter implements BootcampPersistencePort {
    private final BootcampRepository bootcampRepository;
    private final BootcampMapperI bootcampMapper;

    @Override
    public Mono<Bootcamp> saveBootcamp(Bootcamp bootcamp) {
        return bootcampRepository.save(bootcampMapper.toEntity(bootcamp))
                .map(bootcampMapper::toDomainEntity);
    }

    @Override
    public Mono<Bootcamp> getBootcampByName(String name) {
        return bootcampRepository.findByName(name)
                .map(bootcampMapper::toDomainEntity);
    }
}
