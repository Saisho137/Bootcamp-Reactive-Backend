package co.pragma.model.technology_capacity;

import co.pragma.model.capacity.Capacity;
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
