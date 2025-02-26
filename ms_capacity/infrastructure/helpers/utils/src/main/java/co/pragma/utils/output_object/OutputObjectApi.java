package co.pragma.utils.output_object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputObjectApi<T> {
    private OutputHeader header;
    private T payload;
}
