package co.pragma.api;

import co.pragma.model.exceptions.TechnologiesAmountException;
import co.pragma.model.exceptions.TechnologiesDuplicatedException;
import co.pragma.model.exceptions.TechnologiesNotFoundException;
import co.pragma.model.utils.output.AbstractOutputObjectApi;
import co.pragma.model.utils.output.OutputObjectApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ControllerAdvisor extends AbstractOutputObjectApi<Void> {
    @ExceptionHandler(TechnologiesAmountException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleTechnologiesAmountException(TechnologiesAmountException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(createErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage())));
    }

    @ExceptionHandler(TechnologiesDuplicatedException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleTechnologiesDuplicatedException(TechnologiesDuplicatedException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(createErrorResponse(String.valueOf(HttpStatus.CONFLICT.value()), exception.getMessage())));
    }

    @ExceptionHandler(TechnologiesNotFoundException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleTechnologiesNotFoundException(TechnologiesNotFoundException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createErrorResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getMessage())));
    }
}
