package co.pragma.ms_bootcamp.infrastructure.entrypoints.controller.advisor;

import co.pragma.ms_bootcamp.domain.enums.ErrorEnum;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityAlreadyAssociatedException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityAmountException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityDuplicationException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityNotFoundException;
import co.pragma.ms_bootcamp.infrastructure.utils.OutputHeader;
import co.pragma.ms_bootcamp.infrastructure.utils.OutputObjectApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@Order(-2)
@RequiredArgsConstructor
public class BootcampErrorHandler implements WebExceptionHandler {
    private final ObjectMapper objectMapper;

    @Override
    public @NonNull Mono<Void> handle(@NonNull ServerWebExchange exchange, @NonNull Throwable ex) {
        HttpStatus status;
        String message = ex.getMessage();

        switch (ex) {
            case CapacityAmountException capacityAmountException -> status = HttpStatus.BAD_REQUEST;
            case CapacityNotFoundException capacityNotFoundException -> status = HttpStatus.NOT_FOUND;
            case CapacityDuplicationException capacityDuplicationException -> status = HttpStatus.BAD_REQUEST;
            case CapacityAlreadyAssociatedException capacityAlreadyAssociatedException -> status = HttpStatus.CONFLICT;
            default -> {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                message = ErrorEnum.ERROR_500.getMessage();
                log.info(ErrorEnum.ERROR_500.getMessage(), ex);
            }
        }

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        OutputObjectApi<Void> errorResponse = new OutputObjectApi<>(
                new OutputHeader(status.value(), message, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)),
                null
        );

        return Mono.fromCallable(() -> objectMapper.writeValueAsBytes(errorResponse))
                .flatMap(bytes -> exchange
                        .getResponse()
                        .writeWith(
                                Mono.just(exchange.getResponse().bufferFactory().wrap(bytes))
                        ))
                .onErrorResume(JsonProcessingException.class, e ->
                        Mono.error(new RuntimeException(ErrorEnum.SERIALIZATION_ERROR.getMessage(), e))
                );
    }
}
