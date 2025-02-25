package co.pragma.r2dbc.mapper;

import co.pragma.model.capacity.Capacity;
import co.pragma.r2dbc.dto.CapacityDTO;

public interface CapacityMapperI {
    Capacity toEntity(CapacityDTO dto);

    CapacityDTO toDto(Capacity entity);
}
