package co.pragma.usecase;

import co.pragma.logic.TechnologyCapacityGateway;
import co.pragma.model.entity.TechnologyCapacity;
import co.pragma.model.integration.input.TechnologyCapacityRequest;
import co.pragma.model.utils.output.AbstractOutputObjectApi;
import co.pragma.model.utils.output.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TechnologyCapacityUseCase extends AbstractOutputObjectApi<TechnologyCapacity> {
    private final TechnologyCapacityGateway technologyCapacityGateway;

    public Mono<OutputObjectApi<List<TechnologyCapacity>>> saveTechnologyCapacityBatch(TechnologyCapacityRequest request) {
        List<Long> uniqueTechnologyIds = request.getTechnologyIds().stream()
                .distinct()
                .toList();

        if (uniqueTechnologyIds.size() < 3 || uniqueTechnologyIds.size() > 20) {
            return Mono.just(createOutputObjectApiList(null, "400", "Debe haber entre 3 y 20 tecnologías únicas."));
        }

        return technologyCapacityGateway.getByCapacityId(request.getCapacityId())
                .collectList()
                .flatMap(existingTechnologies -> {
                    Set<Long> existingTechIds = existingTechnologies.stream()
                            .map(TechnologyCapacity::getTechnologyId)
                            .collect(Collectors.toSet());

                    List<TechnologyCapacity> newTechnologies = uniqueTechnologyIds.stream()
                            .filter(techId -> !existingTechIds.contains(techId))
                            .map(techId -> TechnologyCapacity.builder()
                                    .technologyId(techId)
                                    .capacityId(request.getCapacityId())
                                    .build())
                            .toList();

                    if (newTechnologies.isEmpty()) {
                        return Mono.just(createOutputObjectApiList(null, "409", "Todas las tecnologías ya están registradas."));
                    }

                    return technologyCapacityGateway.saveAll(newTechnologies)
                            .collectList()
                            .map(savedEntities -> createOutputObjectApiList(savedEntities, "200", "Guardado exitosamente."));
                });
    }

    public Mono<OutputObjectApi<List<TechnologyCapacity>>> getByCapacityId(Long capacityId) {
        return technologyCapacityGateway.getByCapacityId(capacityId).collectList()
                .map(technologyCapacityList -> createOutputObjectApiList(technologyCapacityList, "200", "Obtenido exitosamente."))
                .defaultIfEmpty(createOutputObjectApiList(List.of(), "500", "No se pudo obtener el registro"));
    }
}
