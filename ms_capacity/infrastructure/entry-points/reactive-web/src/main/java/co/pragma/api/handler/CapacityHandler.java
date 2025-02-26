package co.pragma.api.handler;

import co.pragma.model.capacity.Capacity;
import co.pragma.usecase.CapacityUseCase;
import co.pragma.utils.output_object.AbstractOutputObjectApi;
import co.pragma.utils.output_object.OutputObjectApi;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CapacityHandler extends AbstractOutputObjectApi<Capacity> {
    private final CapacityUseCase capacityUseCase;

    public Mono<OutputObjectApi<Capacity>> saveCapacity(Capacity capacity) {
        return capacityUseCase.saveCapacity(capacity).map(capacity1 ->
                createOutputObjectApi(capacity1, "200", "Guardado exitoso")
        );
    }
}

