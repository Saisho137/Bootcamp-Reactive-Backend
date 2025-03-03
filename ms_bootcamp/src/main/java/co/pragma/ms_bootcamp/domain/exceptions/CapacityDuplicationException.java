package co.pragma.ms_bootcamp.domain.exceptions;

public class CapacityDuplicatedException extends RuntimeException {
  public CapacityDuplicatedException(String message) {
    super(message);
  }
}
