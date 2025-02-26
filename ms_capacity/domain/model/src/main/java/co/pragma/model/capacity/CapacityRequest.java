package co.pragma.model.capacity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CapacityRequest {
    private String name;
    private String description;
    private List<Long> technologyIds;
}
