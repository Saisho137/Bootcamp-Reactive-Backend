package co.pragma.ms_bootcamp.domain.model.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CapacityWithTechnologies {
    private Capacity capacity;
    private List<TechnologyIdName> technologies;
}
