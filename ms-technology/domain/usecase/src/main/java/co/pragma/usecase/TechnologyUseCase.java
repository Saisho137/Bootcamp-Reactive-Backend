package co.pragma.usecase;

import co.pragma.logic.TechnologyGateway;
import co.pragma.model.entity.Technology;
import co.pragma.model.utils.output.AbstractOutputObjectApi;
import co.pragma.model.utils.output.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TechnologyUseCase extends AbstractOutputObjectApi<Technology> {
    private final TechnologyGateway technologyGateway;

    public Mono<OutputObjectApi<Technology>> getTechnologyByName(String name) {
        return technologyGateway.getTechnologyByName(name)
                .map(technology -> createOutputObjectApi(technology, "200", "Consulta exitosa"))
                .defaultIfEmpty(createOutputObjectApi(null, "404", "No se encontró la tecnología"));
    }
}
