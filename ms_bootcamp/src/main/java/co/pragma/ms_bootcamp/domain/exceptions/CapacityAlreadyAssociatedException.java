package co.pragma.ms_bootcamp.domain.exceptions;

public class CapacityAlreadyAssociatedException extends RuntimeException {
    public CapacityAlreadyAssociatedException(String message) {
        super(message);
    }
}
