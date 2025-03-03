package co.pragma.ms_bootcamp.infrastructure.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputHeader {
    private int status;
    private String message;
    private String timestamp;
}
