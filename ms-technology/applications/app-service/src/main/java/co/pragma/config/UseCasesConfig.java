package co.pragma.config;

import co.pragma.logic.TechnologyGateway;
import co.pragma.r2dbc.mapper.TechnologyMapper;
import co.pragma.r2dbc.repository.TechnologyRepository;
import co.pragma.r2dbc.service.TechnologyService;
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
    public TechnologyMapper technologyMapper() {
        return Mappers.getMapper(TechnologyMapper.class);
    }

    @Bean
    public TechnologyGateway technologyGateway(TechnologyRepository technologyRepository, TechnologyMapper technologyMapper) {
        return new TechnologyService(technologyRepository, technologyMapper);
    }
}
