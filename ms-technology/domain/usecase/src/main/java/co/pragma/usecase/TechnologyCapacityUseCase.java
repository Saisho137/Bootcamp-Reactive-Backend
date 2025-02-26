package co.pragma.usecase;

import co.pragma.logic.TechnologyCapacityGateway;
import co.pragma.logic.TechnologyGateway;
import co.pragma.model.entity.Technology;
import co.pragma.model.entity.TechnologyCapacity;
import co.pragma.model.exceptions.TechnologiesAmountException;
import co.pragma.model.exceptions.TechnologiesDuplicatedException;
import co.pragma.model.exceptions.TechnologiesNotFoundException;
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
    private final TechnologyGateway technologyGateway;

    public Mono<OutputObjectApi<List<TechnologyCapacity>>> saveTechnologyCapacityBatch(TechnologyCapacityRequest request) {
        List<Long> uniqueTechnologyIds = request.getTechnologyIds().stream()
                .distinct()
                .toList();

        if (uniqueTechnologyIds.size() < 3 || uniqueTechnologyIds.size() > 20) {
            return Mono.error(new TechnologiesAmountException("Deben haber entre 3 y 20 tecnologías."));
        }

        return technologyGateway.getAllTechnologiesByIds(uniqueTechnologyIds)
                .map(Technology::getId).collectList()
                .flatMap(existingIds -> {
                    List<Long> missingIds = uniqueTechnologyIds.stream()
                            .filter(id -> !existingIds.contains(id))
                            .toList();

                    if (!missingIds.isEmpty()) {
                        return Mono.error(
                                new TechnologiesNotFoundException("Los siguientes IDs de tecnología no existen: " + missingIds)
                        );
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
                                    return Mono.error(new TechnologiesDuplicatedException("No se pueden guardar los datos, ya se han agregado las tecnologías seleccionadas."));
                                }

                                return technologyCapacityGateway.saveAll(newTechnologies)
                                        .collectList()
                                        .map(savedEntities -> createOutputObjectApiList(savedEntities, "200", "Guardado exitosamente."));
                            });
                });
    }

    public Mono<OutputObjectApi<List<TechnologyCapacity>>> getByCapacityId(Long capacityId) {
        return technologyCapacityGateway.getByCapacityId(capacityId).collectList()
                .map(technologyCapacityList -> createOutputObjectApiList(technologyCapacityList, "200", "Obtenido exitosamente."))
                .defaultIfEmpty(createOutputObjectApiList(List.of(), "500", "No se pudo obtener el registro"));
    }

    public Mono<List<Long>> getTechnologiesByCapacityId (Long capacityId) {
        return technologyCapacityGateway.getTechnologiesByCapacityId(capacityId).collectList();
    }
}
