package co.pragma.ms_bootcamp.application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "ms_bootcamp",
        version = "1.0.0",
        description = "API para el servicio de bootcamps"
))
public class SwaggerConfig {
}
