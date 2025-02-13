package co.pragma.config;

import co.pragma.logic.TechnologyGateway;
import co.pragma.usecase.TechnologyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {
    @Bean
    public TechnologyUseCase technologyUseCase(TechnologyGateway technologyGateway) {
        return new TechnologyUseCase(technologyGateway);
    }
}
