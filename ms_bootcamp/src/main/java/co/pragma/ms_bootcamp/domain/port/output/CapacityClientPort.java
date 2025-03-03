package co.pragma.ms_bootcamp.domain.port.output;

import co.pragma.ms_bootcamp.application.dto.CapacityIdList;
import co.pragma.ms_bootcamp.domain.model.integration.CapacityWithTechnologies;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapacityClientPort {
    Mono<Boolean> confirmCapacities(CapacityIdList capacitiesIds);
    Flux<CapacityWithTechnologies> getCapacitiesAndTechnologies(List<Long> capacityId);
}
