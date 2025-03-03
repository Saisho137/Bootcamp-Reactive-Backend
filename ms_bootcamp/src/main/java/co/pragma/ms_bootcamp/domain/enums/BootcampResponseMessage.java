package co.pragma.ms_bootcamp.domain.enums;

public enum BootcampResponseMessage {
    BOOTCAMP_SAVED("Guardado exitoso");

    private final String message;

    BootcampResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
