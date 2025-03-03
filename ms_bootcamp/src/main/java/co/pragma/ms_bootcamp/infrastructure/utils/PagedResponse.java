package co.pragma.ms_bootcamp.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {
    private int pageSize;
    private int totalPages;
    private int currentPage;
    private int totalElements;
    private List<T> items;
}
