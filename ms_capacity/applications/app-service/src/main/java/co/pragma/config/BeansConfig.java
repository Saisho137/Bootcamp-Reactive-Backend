package co.pragma.config;

import co.pragma.api.handler.CapacityHandler;
import co.pragma.api.handler.CapacityPaginatedHandler;
import co.pragma.api.handler.CapacityTechnologiesPaginatedHandler;
import co.pragma.model.capacity.gateway.CapacityGateway;
import co.pragma.model.technology_capacity.gateway.TechnologyCapacityGateway;
import co.pragma.r2dbc.mapper.CapacityMapper;
import co.pragma.r2dbc.mapper.CapacityMapperI;
import co.pragma.r2dbc.repository.CapacityRepository;
import co.pragma.r2dbc.service.CapacityService;
import co.pragma.technology.TechnologyService;
import co.pragma.usecase.CapacityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeansConfig {
    @Bean
    public CapacityGateway capacityGateway(CapacityRepository capacityRepository, CapacityMapperI capacityMapperI) {
        return new CapacityService(capacityRepository, capacityMapperI);
    }

    @Bean
    public TechnologyCapacityGateway technologyCapacityGateway(WebClient webClient) {
        return new TechnologyService(webClient);
    }

    @Bean
    public CapacityUseCase capacityUseCase(CapacityGateway capacityGateway, TechnologyCapacityGateway technologyCapacityGateway) {
        return new CapacityUseCase(capacityGateway, technologyCapacityGateway);
    }

    @Bean
    public CapacityHandler capacityHandler(CapacityUseCase capacityUseCase) {
        return new CapacityHandler(capacityUseCase);
    }

    @Bean
    public CapacityPaginatedHandler capacityPaginatedHandlerHandler(CapacityUseCase capacityUseCase) {
        return new CapacityPaginatedHandler(capacityUseCase);
    }

    @Bean
    public CapacityTechnologiesPaginatedHandler capacityTechnologiesPaginatedHandler(CapacityUseCase capacityUseCase) {
        return new CapacityTechnologiesPaginatedHandler(capacityUseCase);
    }

    @Bean
    public CapacityMapperI capacityMapperI() {
        return new CapacityMapper();
    }
}
