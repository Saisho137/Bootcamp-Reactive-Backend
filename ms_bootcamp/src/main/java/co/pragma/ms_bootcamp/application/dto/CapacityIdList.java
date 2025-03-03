package co.pragma.ms_bootcamp.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CapacityIdList {
    List<Long> ids;
}
