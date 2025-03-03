package co.pragma.ms_bootcamp.infrastructure.adapter.web.controller.advisor;

import co.pragma.ms_bootcamp.domain.exceptions.CapacityAlreadyAssociatedException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityAmountException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityDuplicationException;
import co.pragma.ms_bootcamp.domain.exceptions.CapacityNotFoundException;
import co.pragma.ms_bootcamp.infrastructure.utils.AbstractOutputObjectApi;

import co.pragma.ms_bootcamp.infrastructure.utils.OutputObjectApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;


@ControllerAdvice
public class BootcampControllerAdvisor extends AbstractOutputObjectApi<Void> {
    @ExceptionHandler(CapacityAmountException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleCapacityAmountException(CapacityAmountException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(createErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage())));
    }

    @ExceptionHandler(CapacityNotFoundException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleCapacityNotFoundException(CapacityNotFoundException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage())));
    }

    @ExceptionHandler(CapacityDuplicationException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleCapacityDuplicationException(CapacityDuplicationException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(createErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage())));
    }

    @ExceptionHandler(CapacityAlreadyAssociatedException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleCapacityAlreadyAssociatedException(CapacityAlreadyAssociatedException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(createErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage())));
    }
}
