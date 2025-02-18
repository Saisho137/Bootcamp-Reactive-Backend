package co.pragma.api;

import co.pragma.model.entity.Technology;
import co.pragma.model.utils.output.OutputObjectApi;
import co.pragma.usecase.TechnologyUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/technology")
public class TechnologyController {
    TechnologyUseCase technologyUseCase;

    public TechnologyController(TechnologyUseCase technologyUseCase) {
        this.technologyUseCase = technologyUseCase;
    }

    @GetMapping(value = "/get-technology-by-name")
    public Mono<OutputObjectApi<Technology>> getTechnology(@RequestParam(value = "name") String name) {
        return technologyUseCase.getTechnologyByName(name);
    }

    @PostMapping(value = "/save-technology")
    public Mono<OutputObjectApi<Technology>> saveTechnology(@RequestBody Technology technology) {
        return technologyUseCase.saveTechnology(technology);
    }
}
