package co.pragma.config;

import co.pragma.model.capacity.gateway.CapacityGateway;
import co.pragma.r2dbc.mapper.CapacityMapper;
import co.pragma.r2dbc.mapper.CapacityMapperI;
import co.pragma.r2dbc.repository.CapacityRepository;
import co.pragma.r2dbc.service.CapacityService;
import co.pragma.usecase.CapacityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public CapacityGateway capacityGateway(CapacityRepository capacityRepository, CapacityMapperI capacityMapperI) {
        return new CapacityService(capacityRepository, capacityMapperI);
    }

    @Bean
    public CapacityUseCase capacityUseCase(CapacityGateway capacityGateway) {
        return new CapacityUseCase(capacityGateway);
    }

    @Bean
    public CapacityMapperI capacityMapperI() {
        return new CapacityMapper();
    }
}
