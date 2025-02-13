package co.pragma.model.utils.output;


import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public abstract class AbstractOutputObjectApi<T> {

    public OutputObjectApi<T> createOutputObjectApi(T requestObject, String status, String message) {
        OutputHeader header = OutputHeader.builder()
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        return OutputObjectApi.<T>builder()
                .header(header)
                .payload(requestObject)
                .build();
    }
}
