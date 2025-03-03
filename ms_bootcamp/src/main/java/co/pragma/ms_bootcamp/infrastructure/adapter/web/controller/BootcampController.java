package co.pragma.ms_bootcamp.infrastructure.adapter.web.controller;

import co.pragma.ms_bootcamp.application.dto.BootcampRequest;
import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.domain.model.BootcampWithChildren;
import co.pragma.ms_bootcamp.infrastructure.adapter.web.handler.BootcampHandler;
import co.pragma.ms_bootcamp.infrastructure.adapter.web.handler.BootcampWithChildrenHandler;
import co.pragma.ms_bootcamp.infrastructure.utils.OutputObjectApi;
import co.pragma.ms_bootcamp.infrastructure.utils.PagedResponse;
import co.pragma.ms_bootcamp.infrastructure.utils.constants.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_PATH)
public class BootcampController {
    private final BootcampHandler bootcampHandler;
    private final BootcampWithChildrenHandler bootcampWithChildrenHandler;

    @PostMapping(value = ApiPaths.SAVE_BOOTCAMP)
    public Mono<OutputObjectApi<Bootcamp>> saveBootcamp(@RequestBody BootcampRequest request) {
        return bootcampHandler.saveBootcamp(request);
    }

    @GetMapping(value = ApiPaths.GET_ALL_BOOTCAMPS)
    public Mono<OutputObjectApi<PagedResponse<BootcampWithChildren>>> getAllBootcamps(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "asc") String sort,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy) {

        return bootcampWithChildrenHandler.getAllBootcamps(page, size, sort, sortBy);
    }
}
