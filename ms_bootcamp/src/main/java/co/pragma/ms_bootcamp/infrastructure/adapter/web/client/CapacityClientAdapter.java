package co.pragma.ms_bootcamp.infrastructure.adapter.web.client;

import co.pragma.ms_bootcamp.application.dto.CapacityIdList;
import co.pragma.ms_bootcamp.domain.model.integration.CapacityWithTechnologies;
import co.pragma.ms_bootcamp.domain.port.output.CapacityClientPort;
import co.pragma.ms_bootcamp.infrastructure.utils.constants.WebClientEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapacityClientAdapter implements CapacityClientPort {
    private final WebClient webClient;

    @Override
    public Mono<Boolean> confirmCapacities(CapacityIdList capacitiesIds) {
        return webClient.post()
                .uri(WebClientEndpoints.VERIFY_CAPACITIES)
                .bodyValue(capacitiesIds)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    public Flux<CapacityWithTechnologies> getCapacitiesAndTechnologies(List<Long> capacityId) {
        return webClient.post()
                .uri(WebClientEndpoints.GET_ALL_CAPACITIES_AND_TECHNOLOGIES)
                .bodyValue(CapacityIdList.builder().ids(capacityId).build())
                .retrieve()
                .bodyToFlux(CapacityWithTechnologies.class);
    }
}
