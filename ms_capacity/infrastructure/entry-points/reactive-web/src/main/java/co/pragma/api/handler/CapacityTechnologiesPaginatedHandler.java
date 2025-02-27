package co.pragma.api.handler;

import co.pragma.model.technology_capacity.CapacityWithTechnologies;
import co.pragma.utils.integration.output.PagedResponse;
import co.pragma.usecase.CapacityUseCase;
import co.pragma.utils.output_object.AbstractOutputObjectApi;
import co.pragma.utils.output_object.OutputHeader;
import co.pragma.utils.output_object.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class CapacityTechnologiesPaginatedHandler extends AbstractOutputObjectApi<PagedResponse<CapacityWithTechnologies>> {
    private final CapacityUseCase capacityUseCase;

    public Mono<OutputObjectApi<PagedResponse<CapacityWithTechnologies>>> getAllCapacity(int page, int size, String sort, String sortBy) {
        return capacityUseCase.getAllCapacityWithTechnologies(page, size, sort, sortBy)
                .collectList()
                .flatMap(list ->
                        capacityUseCase.countAllCapacity().map(total -> {
                            int totalPages = (int) Math.ceil((double) total / size);
                            List<CapacityWithTechnologies> content = list.stream().toList();

                            return OutputObjectApi.<PagedResponse<CapacityWithTechnologies>>builder()
                                    .header(OutputHeader.builder()
                                            .status("200")
                                            .message("Consulta exitosa")
                                            .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                                            .build())
                                    .payload(PagedResponse.<CapacityWithTechnologies>builder()
                                            .pageSize(size)
                                            .totalPages(totalPages)
                                            .currentPage(page)
                                            .totalElements(total.intValue())
                                            .items(content)
                                            .build()
                                    ).build();
                        })
                );
    }
}

