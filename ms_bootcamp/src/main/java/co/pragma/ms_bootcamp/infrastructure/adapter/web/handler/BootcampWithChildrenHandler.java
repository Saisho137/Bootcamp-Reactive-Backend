package co.pragma.ms_bootcamp.infrastructure.adapter.web.handler;

import co.pragma.ms_bootcamp.domain.enums.BootcampResponseMessage;
import co.pragma.ms_bootcamp.domain.model.BootcampWithChildren;
import co.pragma.ms_bootcamp.domain.port.input.BootcampPort;
import co.pragma.ms_bootcamp.infrastructure.utils.AbstractOutputObjectApi;
import co.pragma.ms_bootcamp.infrastructure.utils.OutputObjectApi;
import co.pragma.ms_bootcamp.infrastructure.utils.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class BootcampWithChildrenHandler extends AbstractOutputObjectApi<PagedResponse<BootcampWithChildren>> {
    private final BootcampPort bootcampPort;

    public Mono<OutputObjectApi<PagedResponse<BootcampWithChildren>>> getAllBootcamps(int page, int size, String sort, String sortBy) {
        return bootcampPort.getAllBootcamps(page, size, sort, sortBy)
                .collectList()
                .flatMap(list -> bootcampPort.countAllBootcamps().map(total -> {
                    int totalPages = (int) Math.ceil((double) total / size);
                    List<BootcampWithChildren> content = list.stream().toList();

                    return createOutputObjectApi(PagedResponse.<BootcampWithChildren>builder()
                            .pageSize(size)
                            .totalPages(totalPages)
                            .currentPage(page)
                            .totalElements(total.intValue())
                            .items(content)
                            .build(), HttpStatus.OK.value(), BootcampResponseMessage.BOOTCAMP_QUERY.getMessage());
                }));
    }
}
