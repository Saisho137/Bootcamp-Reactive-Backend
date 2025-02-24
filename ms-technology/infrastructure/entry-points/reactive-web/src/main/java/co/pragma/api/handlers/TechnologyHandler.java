package co.pragma.api.handlers;

import co.pragma.model.entity.Technology;
import co.pragma.model.integration.output.TechnologyPaginated;
import co.pragma.model.utils.output.AbstractOutputObjectApi;
import co.pragma.model.utils.output.OutputObjectApi;
import co.pragma.usecase.TechnologyUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class TechnologyHandler extends AbstractOutputObjectApi<TechnologyPaginated> {
    private final TechnologyUseCase technologyUseCase;

    public Mono<OutputObjectApi<TechnologyPaginated>> getAllTechnologies(int page, int size, String sort) {
        return technologyUseCase.getAllTechnologies(page, size, sort).map(tuple -> {
            List<Technology> content = tuple.getT1();
            long total = tuple.getT2();
            int totalPages = (int) Math.ceil((double) total / size);

            return createOutputObjectApi(
                    TechnologyPaginated.builder()
                            .technologies(content)
                            .totalElements((int) total)
                            .totalPages(totalPages)
                            .currentPage(page)
                            .pageSize(size)
                            .build(),
                    "200", "Consulta exitosa"
            );
        });
    }
}
