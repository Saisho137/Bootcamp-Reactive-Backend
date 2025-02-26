package co.pragma.utils.output_object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputHeader {
    private String status;
    private String message;
    private String timestamp;
}
