package co.pragma.utils.integration.output;

import java.util.List;

public class PagedResponse<T> {
    private int pageSize;
    private int totalPages;
    private int currentPage;
    private int totalElements;
    private List<T> items;
}
