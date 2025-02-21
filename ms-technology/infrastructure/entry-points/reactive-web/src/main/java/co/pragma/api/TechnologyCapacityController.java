package co.pragma.api;

import co.pragma.model.entity.TechnologyCapacity;
import co.pragma.model.integration.input.TechnologyCapacityRequest;
import co.pragma.model.utils.output.OutputObjectApi;
import co.pragma.usecase.TechnologyCapacityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/technology-capacity")
public class TechnologyCapacityController {
    private final TechnologyCapacityUseCase technologyCapacityUseCase;

    @PostMapping(value = "/save-technology-capacity")
    public Mono<OutputObjectApi<List<TechnologyCapacity>>> saveTechnologyCapacity(@RequestBody TechnologyCapacityRequest request) {
        return technologyCapacityUseCase.saveTechnologyCapacityBatch(request);
    }

    @GetMapping(value = "/get-technology-capacity-by-capacity-id")
    public Mono<OutputObjectApi<List<TechnologyCapacity>>> getByCapacityId(@RequestParam(value = "capacityId") Long capacityId) {
        return technologyCapacityUseCase.getByCapacityId(capacityId);
    }
}
