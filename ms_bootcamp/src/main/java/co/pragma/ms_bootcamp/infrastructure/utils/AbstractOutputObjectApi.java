package co.pragma.ms_bootcamp.infrastructure.utils;


import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public abstract class AbstractOutputObjectApi<T> {

    public OutputObjectApi<T> createOutputObjectApi(T payload, int status, String message) {
        OutputHeader header = OutputHeader.builder()
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        return OutputObjectApi.<T>builder()
                .header(header)
                .payload(payload)
                .build();
    }

    public OutputObjectApi<List<T>> createOutputObjectApiList(List<T> payload, int status, String message) {
        OutputHeader header = OutputHeader.builder()
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        return OutputObjectApi.<List<T>>builder()
                .header(header)
                .payload(payload)
                .build();
    }

    public OutputObjectApi<T> createErrorResponse(int status, String message) {
        OutputHeader header = OutputHeader.builder()
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        return OutputObjectApi.<T>builder()
                .header(header)
                .payload(null)
                .build();
    }
}
