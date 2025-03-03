package co.pragma.ms_bootcamp.domain.enums;

public enum BootcampResponseMessage {
    BOOTCAMP_SAVED("Guardado exitoso"),
    BOOTCAMP_QUERY("Consulta exitosa");

    private final String message;

    BootcampResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
