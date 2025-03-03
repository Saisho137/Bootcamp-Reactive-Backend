package co.pragma.api.advisor;

import co.pragma.exceptions.CapacityAlreadyHaveTheseTechnologiesException;
import co.pragma.exceptions.TechnologiesAmountException;
import co.pragma.exceptions.TechnologiesNotFoundException;
import co.pragma.utils.output_object.AbstractOutputObjectApi;
import co.pragma.utils.output_object.OutputObjectApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class CapacityControllerAdvisor extends AbstractOutputObjectApi<Void> {
    @ExceptionHandler(TechnologiesAmountException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleTechnologiesAmountException(TechnologiesAmountException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(createErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage())));
    }

    @ExceptionHandler(TechnologiesNotFoundException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleTechnologiesNotFoundException(TechnologiesNotFoundException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createErrorResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getMessage())));
    }

    @ExceptionHandler(CapacityAlreadyHaveTheseTechnologiesException.class)
    public Mono<ResponseEntity<OutputObjectApi<Void>>> handleCapacityAlreadyHaveTheseTechnologiesException(CapacityAlreadyHaveTheseTechnologiesException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(createErrorResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getMessage())));
    }
}
