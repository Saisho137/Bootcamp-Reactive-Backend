package co.pragma.ms_bootcamp.domain.port.output;

import co.pragma.ms_bootcamp.application.dto.CapacityIdList;
import reactor.core.publisher.Mono;

public interface CapacityClientPort {
    Mono<Boolean> confirmCapacities(CapacityIdList capacitiesIds);
}
