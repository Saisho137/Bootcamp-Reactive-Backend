package co.pragma.usecase;

import co.pragma.logic.TechnologyGateway;
import co.pragma.model.entity.Technology;
import co.pragma.model.integration.input.TechnologyIds;
import co.pragma.model.utils.output.AbstractOutputObjectApi;
import co.pragma.model.utils.output.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

@RequiredArgsConstructor
public class TechnologyUseCase extends AbstractOutputObjectApi<Technology> {
    private final TechnologyGateway technologyGateway;

    public Mono<OutputObjectApi<Technology>> getTechnologyByName(String name) {
        return technologyGateway.getTechnologyByName(name)
                .map(technology -> createOutputObjectApi(technology, "200", "Consulta exitosa"))
                .defaultIfEmpty(createOutputObjectApi(null, "404", "No se encontró la tecnología"));
    }

    public Mono<OutputObjectApi<Technology>> saveTechnology(Technology technology) {
        return technologyGateway.saveTechnology(technology)
                .map(technology1 -> createOutputObjectApi(technology1, "200", "Guardado exitoso"))
                .defaultIfEmpty(createOutputObjectApi(null, "500", "No se pudo guardar la tecnología"));
    }

    public Mono<Tuple2<List<Technology>, Long>> getAllTechnologies(int page, int size, String sort) {
        Mono<List<Technology>> technologies = technologyGateway.getAllTechnologies(page, size, sort).collectList();
        Mono<Long> totalElements = technologyGateway.countAllTechnologies();

        return Mono.zip(technologies, totalElements);
    }

    public Mono<Boolean> confirmTechnologies(TechnologyIds technologyIds) {
        return technologyGateway.confirmTechnologies(technologyIds.getIds());
    }
}
