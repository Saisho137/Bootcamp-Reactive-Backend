package co.pragma.r2dbc.mapper;

import co.pragma.model.entity.TechnologyCapacity;
import co.pragma.r2dbc.dto.TechnologyCapacityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechnologyCapacityMapper {
    TechnologyCapacityDTO toDto(TechnologyCapacity technologyCapacityEntity);

    TechnologyCapacity toEntity(TechnologyCapacityDTO technologyCapacityDTO);
}
