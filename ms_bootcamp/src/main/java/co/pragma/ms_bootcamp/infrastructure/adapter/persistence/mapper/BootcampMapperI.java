package co.pragma.ms_bootcamp.infrastructure.adapter.persistence.mapper;

import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.entity.BootcampEntity;

public interface BootcampMapperI {
    Bootcamp toDomainEntity(BootcampEntity entity);

    BootcampEntity toEntity(Bootcamp domainEntity);
}
