package co.pragma.ms_bootcamp.infrastructure.adapter.web.controller;

import co.pragma.ms_bootcamp.application.dto.BootcampRequest;
import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.infrastructure.adapter.web.handler.BootcampHandler;
import co.pragma.ms_bootcamp.infrastructure.utils.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bootcamp")
public class BootcampController {
    private final BootcampHandler bootcampHandler;

    @PostMapping(value = "/save-bootcamp")
    public Mono<OutputObjectApi<Bootcamp>> saveBootcamp(@RequestBody BootcampRequest request) {
        return bootcampHandler.saveBootcamp(request);
    }
}
