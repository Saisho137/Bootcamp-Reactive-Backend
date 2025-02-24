package co.pragma.config;

import co.pragma.api.handlers.TechnologyHandler;
import co.pragma.logic.TechnologyCapacityGateway;
import co.pragma.logic.TechnologyGateway;
import co.pragma.r2dbc.mapper.TechnologyCapacityMapper;
import co.pragma.r2dbc.mapper.TechnologyMapper;
import co.pragma.r2dbc.repository.TechnologyCapacityRepository;
import co.pragma.r2dbc.repository.TechnologyRepository;
import co.pragma.r2dbc.service.TechnologyCapacityService;
import co.pragma.r2dbc.service.TechnologyService;
import co.pragma.usecase.TechnologyCapacityUseCase;
import co.pragma.usecase.TechnologyUseCase;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {
    @Bean
    public TechnologyUseCase technologyUseCase(TechnologyGateway technologyGateway) {
        return new TechnologyUseCase(technologyGateway);
    }

    @Bean
    public TechnologyCapacityUseCase technologyCapacityUseCase(TechnologyCapacityGateway technologyCapacityGateway, TechnologyGateway technologyGateway) {
        return new TechnologyCapacityUseCase(technologyCapacityGateway, technologyGateway);
    }

    @Bean
    public TechnologyHandler technologyHandler(TechnologyUseCase technologyUseCase) {
        return new TechnologyHandler(technologyUseCase);
    }

    @Bean
    public TechnologyMapper technologyMapper() {
        return Mappers.getMapper(TechnologyMapper.class);
    }

    @Bean
    public TechnologyCapacityMapper technologyCapacityMapper() {
        return Mappers.getMapper(TechnologyCapacityMapper.class);
    }

    @Bean
    public TechnologyGateway technologyGateway(TechnologyRepository technologyRepository, TechnologyMapper technologyMapper) {
        return new TechnologyService(technologyRepository, technologyMapper);
    }

    @Bean
    public TechnologyCapacityGateway technologyCapacityGateway(TechnologyCapacityRepository technologyCapacityRepository, TechnologyCapacityMapper technologyCapacityMapper) {
        return new TechnologyCapacityService(technologyCapacityRepository, technologyCapacityMapper);
    }
}
