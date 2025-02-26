package co.pragma.api;

import co.pragma.api.handlers.TechnologyHandler;
import co.pragma.model.entity.Technology;
import co.pragma.model.integration.input.TechnologyIds;
import co.pragma.model.integration.output.TechnologyPaginated;
import co.pragma.model.utils.output.OutputObjectApi;
import co.pragma.usecase.TechnologyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/technology")
public class TechnologyController {
    private final TechnologyUseCase technologyUseCase;
    private final TechnologyHandler technologyHandler;

    @PostMapping(value = "/save-technology")
    public Mono<OutputObjectApi<Technology>> saveTechnology(@RequestBody Technology technology) {
        return technologyUseCase.saveTechnology(technology);
    }

    @GetMapping(value = "/get-technology-by-name")
    public Mono<OutputObjectApi<Technology>> getTechnology(@RequestParam(value = "name") String name) {
        return technologyUseCase.getTechnologyByName(name);
    }

    @GetMapping(value = "/get-all-technologies")
    public Mono<OutputObjectApi<TechnologyPaginated>> getAllTechnologies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "asc") String sort) {

        return technologyHandler.getAllTechnologies(page, size, sort);
    }

    @PostMapping(value = "/verify-technologies")
    public Mono<Boolean> verifyTechnologies(@RequestBody TechnologyIds technologyIds) {
        return technologyUseCase.confirmTechnologies(technologyIds);
    }

}
