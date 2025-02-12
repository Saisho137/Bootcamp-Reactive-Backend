package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.massivegeneration.MassiveGeneration;
import co.com.bancolombia.model.request.Request;
import co.com.bancolombia.r2dbc.mapper.GenericMapper;
import co.com.bancolombia.r2dbc.model.MassiveDTO;
import co.com.bancolombia.r2dbc.model.RequestDTO;
import co.com.bancolombia.r2dbc.provider.TestBuilders;
import co.com.bancolombia.r2dbc.repository.MassiveDBRepository;
import co.com.bancolombia.r2dbc.repository.RequestDBRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RequestServiceTest {

    @Mock
    private RequestDBRepository requestDBRepository;

    private RequestService requestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var genericMapper = new GenericMapper();
        this.requestService = new RequestService(requestDBRepository, genericMapper);
    }

    @Test
    void getRequestsByIdMassiveTest() {
        UUID id = UUID.randomUUID();
        when(requestDBRepository.findByIdMassive(id))
                .thenReturn(Flux.just(TestBuilders.getRequest()));
        StepVerifier.create(requestService.getRequestsByIdMassive(id))
                .expectSubscription()
                .expectComplete()
                .verifyLater();
    }

    @Test
    void genericMapperRequestTest() {
        GenericMapper genericMapperTest = new GenericMapper();
        RequestDTO requestDTO = TestBuilders.getRequest();
        Request request = genericMapperTest.getRequest(requestDTO);
        assertEquals(request.getIdRequest(), requestDTO.getIdRequest());
    }
}
