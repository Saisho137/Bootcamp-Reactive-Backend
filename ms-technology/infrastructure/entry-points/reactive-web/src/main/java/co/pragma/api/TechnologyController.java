package co.pragma.api;

import co.pragma.model.entity.Technology;
import co.pragma.model.utils.output.OutputObjectApi;
import co.pragma.usecase.TechnologyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/technology")
public class TechnologyController {
    private final TechnologyUseCase technologyUseCase;

    @PostMapping(value = "/save-technology")
    public Mono<OutputObjectApi<Technology>> saveTechnology(@RequestBody Technology technology) {
        return technologyUseCase.saveTechnology(technology);
    }

    @GetMapping(value = "/get-technology-by-name")
    public Mono<OutputObjectApi<Technology>> getTechnology(@RequestParam(value = "name") String name) {
        return technologyUseCase.getTechnologyByName(name);
    }

    @GetMapping(value = "/get-all-technologies")
    public Mono<OutputObjectApi<List<Technology>>> getAllTechnologies(@RequestParam(value = "sort", required = false) String sort) {
        return technologyUseCase.getAllTechnologies(sort == null ? "asc" : sort);
    }

}
