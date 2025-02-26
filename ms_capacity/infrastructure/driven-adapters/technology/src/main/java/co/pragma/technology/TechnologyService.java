package co.pragma.technology;

import co.pragma.model.technology_capacity.gateway.TechnologyCapacityGateway;
import co.pragma.utils.integration.input.TechnologyCapacityRequest;
import co.pragma.model.technology_capacity.TechnologyIds;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyService implements TechnologyCapacityGateway {
    private final WebClient webClient;
    private static final String TECHNOLOGY_URI_CONTROLLER = "/api/v1/technology";
    private static final String TECHNOLOGY_CAPACITY_URI_CONTROLLER = "/api/v1/technology-capacity";


    @Override
    public Mono<Boolean> confirmTechnologies(TechnologyIds technologyIds) {
        return webClient.post()
                .uri(TECHNOLOGY_URI_CONTROLLER + "/verify-technologies")
                .bodyValue(technologyIds)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    public Mono<Void> saveTechnologiesFromCapacity(Long capacityId, List<Long> technologyIds) {
        return webClient.post()
                .uri(TECHNOLOGY_CAPACITY_URI_CONTROLLER + "/save-technology-capacity")
                .bodyValue(TechnologyCapacityRequest.builder()
                        .technologyIds(technologyIds)
                        .capacityId(capacityId)
                        .build())
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Flux<Long> getTechnologiesByCapacityId(Long capacityId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(TECHNOLOGY_CAPACITY_URI_CONTROLLER + "/get-technologies-by-capacity-id")
                        .queryParam("capacityId", capacityId)
                        .build()
                )
                .retrieve()
                .bodyToFlux(Long.class);
    }
}
