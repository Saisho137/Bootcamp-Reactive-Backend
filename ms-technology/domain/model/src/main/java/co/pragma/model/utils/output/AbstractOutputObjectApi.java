package co.pragma.model.utils.output;


import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public abstract class AbstractOutputObjectApi<T> {

    public OutputObjectApi<T> createOutputObjectApi(T payload, String status, String message) {
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

    public OutputObjectApi<List<T>> createOutputObjectApiList(List<T> payload, String status, String message) {
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

    public OutputObjectApi<T> createErrorResponse(String status, String message) {
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
