package co.pragma.ms_bootcamp.domain.enums;

public enum BootcampErrorMessage {
    CAPACITIES_DUPLICATED("Los ids de Capacidades no pueden repetirse"),
    CAPACITIES_AMOUNT_INVALID("El número de Capacidades debe ser entre 1 y 4"),
    CAPACITIES_NOT_FOUND("No se pueden guardar los datos, 1 o varios IDs de capacities no existen"),
    CAPACITIES_EXCEED_LIMIT("El número de capacidades asociadas no puede superar 4"),
    CAPACITIES_ALREADY_ASSOCIATED("Todas las capacidades enviadas ya estaban asociadas al Bootcamp!");

    private final String message;

    BootcampErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
