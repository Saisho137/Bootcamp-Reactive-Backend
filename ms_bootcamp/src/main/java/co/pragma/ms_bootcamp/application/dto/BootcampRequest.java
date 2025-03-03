package co.pragma.ms_bootcamp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BootcampRequest {
    private String name;
    private String description;
    private List<Long> capacitiesIds;
}
