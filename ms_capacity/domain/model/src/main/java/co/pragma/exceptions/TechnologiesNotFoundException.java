package co.pragma.exceptions;

public class TechnologiesNotFoundException extends RuntimeException {
    public TechnologiesNotFoundException(String message) {
        super(message);
    }
}
