package co.pragma.ms_bootcamp.domain.model;

import co.pragma.ms_bootcamp.domain.model.integration.CapacityWithTechnologies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BootcampWithChildren {
    private Bootcamp bootcamp;
    List<CapacityWithTechnologies> capacities;
}
