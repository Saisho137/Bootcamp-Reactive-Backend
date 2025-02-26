package co.pragma.api.controller;

import co.pragma.api.handler.CapacityHandler;
import co.pragma.api.handler.CapacityPaginatedHandler;
import co.pragma.model.capacity.Capacity;
import co.pragma.model.capacity.CapacityRequest;
import co.pragma.utils.integration.output.CapacityPaginated;
import co.pragma.utils.output_object.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/capacity")
public class CapacityController {
    private final CapacityHandler capacityHandler;
    private final CapacityPaginatedHandler capacityPaginatedHandler;

    @GetMapping(value = "/get-all-capacities")
    public Mono<OutputObjectApi<CapacityPaginated>> getAllTechnologies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "asc") String sort) {

        return capacityPaginatedHandler.getAllCapacity(page, size, sort);
    }

    @PostMapping(value = "/save-capacity")
    public Mono<OutputObjectApi<Capacity>> saveCapacity(@RequestBody CapacityRequest request) {
        return capacityHandler.saveCapacity(request);
    }
}
