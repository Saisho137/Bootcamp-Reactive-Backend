package co.pragma.usecase;

import co.pragma.logic.TechnologyGateway;
import co.pragma.model.entity.Technology;
import co.pragma.model.utils.output.AbstractOutputObjectApi;
import co.pragma.model.utils.output.OutputHeader;
import co.pragma.model.utils.output.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Mono<OutputObjectApi<List<Technology>>> getAllTechnologies(String sort) {
            return technologyGateway.getAllTechnologies(sort)
                    .collectList()
                    .map(technologies -> createOutputObjectApiList(
                            technologies, "200", "Consulta exitosa"
                    ))
                    .defaultIfEmpty(createOutputObjectApiList(
                            List.of(), "404", "No se encontraron tecnologías"
                    ));

    }
}
