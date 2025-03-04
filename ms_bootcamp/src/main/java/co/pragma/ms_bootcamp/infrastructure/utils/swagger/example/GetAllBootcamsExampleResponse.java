package co.pragma.ms_bootcamp.infrastructure.utils.swagger.example;

import co.pragma.ms_bootcamp.domain.model.BootcampWithChildren;
import co.pragma.ms_bootcamp.infrastructure.utils.OutputObjectApi;
import co.pragma.ms_bootcamp.infrastructure.utils.PagedResponse;

public class GetAllBootcamsExampleResponse extends OutputObjectApi<PagedResponse<BootcampWithChildren>> {
    public GetAllBootcamsExampleResponse() {
        super(null, null);
    }
}
