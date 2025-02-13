package co.pragma.r2dbc.mapper;

import co.pragma.model.entity.Technology;
import co.pragma.r2dbc.dto.TechnologyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechnologyMapper {
    TechnologyDTO toDto(Technology technologyEntity);

    Technology toEntity(TechnologyDTO technologyDTO);
}
