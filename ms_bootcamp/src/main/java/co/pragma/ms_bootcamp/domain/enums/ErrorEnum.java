package co.pragma.ms_bootcamp.domain.enums;

public enum ErrorEnum {
    ERROR_500("Unexpected error occurred"),
    SERIALIZATION_ERROR("There was an error serializing the response");

    private final String message;

    ErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
