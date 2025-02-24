package co.pragma.model.integration.output;

import co.pragma.model.entity.Technology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyPaginated {

    private int pageSize;
    private int totalPages;
    private int currentPage;
    private int totalElements;
    private List<Technology> technologies;
}
