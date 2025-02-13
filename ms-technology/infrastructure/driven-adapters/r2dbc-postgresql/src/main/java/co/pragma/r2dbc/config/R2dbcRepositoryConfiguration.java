package co.pragma.r2dbc.config;

import co.pragma.logic.TechnologyGateway;
import co.pragma.r2dbc.mapper.TechnologyMapper;
import co.pragma.r2dbc.repository.TechnologyRepository;
import co.pragma.r2dbc.service.TechnologyService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Slf4j
@Configuration
@EnableR2dbcRepositories(basePackages = "co.pragma.r2dbc.repositories")
public class R2dbcRepositoryConfiguration {
    public R2dbcRepositoryConfiguration() {
        log.info("R2DBC Repository Configuration INIT....");
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
