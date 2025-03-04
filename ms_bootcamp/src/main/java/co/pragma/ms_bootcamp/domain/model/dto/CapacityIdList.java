package co.pragma.ms_bootcamp.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CapacityIdList {
    List<Long> ids;
}
