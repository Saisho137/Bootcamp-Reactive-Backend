package co.pragma.ms_bootcamp.config;

import co.pragma.ms_bootcamp.application.usecase.BootcampUseCase;
import co.pragma.ms_bootcamp.domain.port.input.BootcampPort;
import co.pragma.ms_bootcamp.domain.port.output.BootcampPersistencePort;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.adapter.BootcampPersistenceAdapter;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.mapper.BootcampMapper;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.mapper.BootcampMapperI;
import co.pragma.ms_bootcamp.infrastructure.adapter.persistence.repository.BootcampRepository;
import co.pragma.ms_bootcamp.infrastructure.adapter.web.handler.BootcampHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public BootcampMapperI bootcampMapperI() {
        return new BootcampMapper();
    }

    @Bean
    public BootcampPersistencePort bootcampPersistencePort(BootcampRepository bootcampRepository, BootcampMapperI bootcampMapperI) {
        return new BootcampPersistenceAdapter(bootcampRepository, bootcampMapperI);
    }

    @Bean
    public BootcampPort bootcampPort(BootcampPersistencePort bootcampPersistencePort) {
        return new BootcampUseCase(bootcampPersistencePort);
    }

    @Bean
    public BootcampHandler bootcampHandler(BootcampPort bootcampPort) {
        return new BootcampHandler(bootcampPort);
    }

}
