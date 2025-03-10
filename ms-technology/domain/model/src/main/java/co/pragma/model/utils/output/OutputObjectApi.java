package co.pragma.model.utils.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputObjectApi<T> {
    private OutputHeader header;
    private T payload;
}
