package co.pragma.ms_bootcamp.infrastructure.adapter.web.client;

import co.pragma.ms_bootcamp.application.dto.CapacityIdList;
import co.pragma.ms_bootcamp.domain.port.output.CapacityClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CapacityClientAdapter implements CapacityClientPort {
    private final WebClient webClient;

    @Override
    public Mono<Boolean> confirmCapacities(CapacityIdList capacitiesIds) {
        return webClient.post()
                .uri("/api/v1/capacity/verify-capacities")
                .bodyValue(capacitiesIds)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
