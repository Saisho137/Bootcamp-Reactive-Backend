package co.pragma.utils.integration.output;

import co.pragma.model.capacity.Capacity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CapacityPaginated {
    private int pageSize;
    private int totalPages;
    private int currentPage;
    private int totalElements;
    private List<Capacity> capacities;
}
