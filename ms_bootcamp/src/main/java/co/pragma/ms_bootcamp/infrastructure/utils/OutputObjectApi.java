package co.pragma.ms_bootcamp.infrastructure.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputObjectApi<T> {
    private OutputHeader header;
    private T payload;
}
