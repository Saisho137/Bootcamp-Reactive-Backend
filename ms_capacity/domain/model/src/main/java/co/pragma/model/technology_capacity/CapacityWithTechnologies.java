package co.pragma.model.technology_capacity;

import co.pragma.model.capacity.Capacity;
import lombok.Builder;

import java.util.List;

@Builder
public class CapacityWithTechnologies {
    private Capacity capacity;
    private List<TechnologyIdName> technologies;
}
