package co.pragma.model.technology_capacity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TechnologyIds {
    private List<Long> ids;
}
