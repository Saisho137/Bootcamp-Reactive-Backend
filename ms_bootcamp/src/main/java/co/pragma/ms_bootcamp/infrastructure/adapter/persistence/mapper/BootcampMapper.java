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
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .capacitiesCount(entity.getCapacitiesCount())
                .capacitiesIds(entity.getCapacitiesIds())
                .build();
    }

    @Override
    public BootcampEntity toEntity(Bootcamp domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        return BootcampEntity.builder()
                .id(domainEntity.getId())
                .name(domainEntity.getName())
                .description(domainEntity.getDescription())
                .capacitiesCount(domainEntity.getCapacitiesCount())
                .capacitiesIds(domainEntity.getCapacitiesIds())
                .build();
    }
}
