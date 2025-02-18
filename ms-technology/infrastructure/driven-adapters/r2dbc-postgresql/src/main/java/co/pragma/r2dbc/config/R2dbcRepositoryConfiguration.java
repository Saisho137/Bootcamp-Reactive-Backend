package co.pragma.r2dbc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Slf4j
@Configuration
@EnableR2dbcRepositories(basePackages = "co.pragma.r2dbc.repository")
public class R2dbcRepositoryConfiguration {
    public R2dbcRepositoryConfiguration() {
        log.info("R2DBC Repository Configuration INIT....");
    }
}
