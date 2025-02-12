package co.com.bancolombia.r2dbc;


import co.com.bancolombia.model.request.Request;
import co.com.bancolombia.model.request.gateways.RequestGateway;
import co.com.bancolombia.r2dbc.mapper.GenericMapper;
import co.com.bancolombia.r2dbc.repository.RequestDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;


import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
public class RequestService implements RequestGateway {

    private final RequestDBRepository requestRepository;
    private final GenericMapper mapper;

    @Override
    public Flux<Request> getRequestsByIdMassive(UUID idMassive) {
        return requestRepository.findByIdMassive(idMassive)
                .map(mapper::getRequest);
    }

}
