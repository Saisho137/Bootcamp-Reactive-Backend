package co.pragma.r2dbc.mapper;

import co.pragma.model.capacity.Capacity;
import co.pragma.r2dbc.dto.CapacityDTO;

public class CapacityMapper implements CapacityMapperI {
    @Override
    public Capacity toEntity(CapacityDTO dto) {
        if (dto == null) {
            return null;
        }
        return Capacity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .technologyCount(dto.getTechnologyCount())
                .build();
    }

    @Override
    public CapacityDTO toDto(Capacity entity) {
        if (entity == null) {
            return null;
        }
        return CapacityDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .technologyCount(entity.getTechnologyCount())
                .build();
    }
}
