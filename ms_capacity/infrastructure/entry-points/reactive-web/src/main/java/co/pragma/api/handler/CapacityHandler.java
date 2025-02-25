package co.pragma.api.handler;

import co.pragma.model.capacity.Capacity;
import co.pragma.usecase.CapacityUseCase;
import co.pragma.utils.integration.output.CapacityPaginated;
import co.pragma.utils.output.AbstractOutputObjectApi;
import co.pragma.utils.output.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CapacityHandler extends AbstractOutputObjectApi<CapacityPaginated> {
    private final CapacityUseCase capacityUseCase;

    public Mono<OutputObjectApi<CapacityPaginated>> getAllCapacity(int page, int size, String sort) {
        return capacityUseCase.getAllCapacity(page, size, sort)
                .collectList()
                .flatMap(list ->
                        capacityUseCase.countAllCapacity().map(total -> {
                            int totalPages = (int) Math.ceil((double) total / size);
                            List<Capacity> content = list.stream().toList();

                            return createOutputObjectApi(
                                    CapacityPaginated.builder()
                                            .capacities(content)
                                            .totalElements(total.intValue())
                                            .totalPages(totalPages)
                                            .currentPage(page)
                                            .pageSize(size)
                                            .build(),
                                    "200", "Consulta exitosa"
                            );
                        })
                );
    }
}

