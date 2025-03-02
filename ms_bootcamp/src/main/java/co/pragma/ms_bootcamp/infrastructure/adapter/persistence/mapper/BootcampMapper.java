package co.pragma.ms_bootcamp.infrastructure.adapter.persistence.mapper;

import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.entity.BootcampEntity;

public class BootcampMapper implements BootcampMapperI {

    @Override
    public Bootcamp toDomainEntity(BootcampEntity entity) {
        if (entity == null) {
            return null;
        }
        return Bootcamp.builder()
                .id(Long.decode(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .capacitiesIds(entity.getCapacitiesIds())
                .build();
    }

    @Override
    public BootcampEntity toEntity(Bootcamp domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        return BootcampEntity.builder()
                .id(Long.toString(domainEntity.getId()))
                .name(domainEntity.getName())
                .description(domainEntity.getDescription())
                .capacitiesIds(domainEntity.getCapacitiesIds())
                .build();
    }
}
