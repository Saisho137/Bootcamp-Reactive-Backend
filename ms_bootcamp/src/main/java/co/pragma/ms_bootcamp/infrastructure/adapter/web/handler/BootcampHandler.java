package co.pragma.ms_bootcamp.infrastructure.adapter.web.handler;

import co.pragma.ms_bootcamp.application.dto.BootcampRequest;
import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.domain.port.input.BootcampPort;
import co.pragma.ms_bootcamp.infrastructure.utils.AbstractOutputObjectApi;
import co.pragma.ms_bootcamp.infrastructure.utils.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampHandler extends AbstractOutputObjectApi<Bootcamp> {
    private final BootcampPort bootcampPort;

    public Mono<OutputObjectApi<Bootcamp>> saveBootcamp(BootcampRequest request) {
        return bootcampPort.saveBootcamp(request).map(bootcamp ->
                createOutputObjectApi(bootcamp, HttpStatus.CREATED.value(), "Guardado exitoso")
        );
    }
}
