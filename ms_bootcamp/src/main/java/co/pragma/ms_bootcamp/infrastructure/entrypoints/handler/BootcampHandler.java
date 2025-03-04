package co.pragma.ms_bootcamp.infrastructure.entrypoints.handler;

import co.pragma.ms_bootcamp.domain.model.dto.BootcampRequest;
import co.pragma.ms_bootcamp.domain.enums.BootcampResponseMessage;
import co.pragma.ms_bootcamp.domain.model.Bootcamp;
import co.pragma.ms_bootcamp.domain.port.input.BootcampPort;
import co.pragma.ms_bootcamp.infrastructure.utils.AbstractOutputObjectApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampHandler extends AbstractOutputObjectApi<Bootcamp> {
    private final BootcampPort bootcampPort;

    public Mono<ServerResponse> saveBootcamp(ServerRequest request) {
        return request.bodyToMono(BootcampRequest.class)
                .flatMap(bootcampPort::saveBootcamp)
                .map(bootcamp -> createOutputObjectApi(
                        bootcamp,
                        HttpStatus.CREATED.value(),
                        BootcampResponseMessage.BOOTCAMP_SAVED.getMessage()
                ))
                .flatMap(output -> ServerResponse.status(HttpStatus.CREATED).bodyValue(output));
    }
}
