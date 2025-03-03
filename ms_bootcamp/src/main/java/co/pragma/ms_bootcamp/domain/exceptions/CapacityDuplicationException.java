package co.pragma.ms_bootcamp.domain.exceptions;

public class CapacityDuplicationException extends RuntimeException {
    public CapacityDuplicationException(String message) {
        super(message);
    }
}
