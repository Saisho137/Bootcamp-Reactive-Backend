package co.pragma.ms_bootcamp.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OutputObjectApi<T> {
    private OutputHeader header;
    private T payload;
}
