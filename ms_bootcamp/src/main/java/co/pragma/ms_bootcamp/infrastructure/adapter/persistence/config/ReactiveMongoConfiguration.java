package co.pragma.ms_bootcamp.infrastructure.adapter.persistence.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Slf4j
@Configuration
@EnableReactiveMongoRepositories(basePackages = "co.pragma.ms_bootcamp.infrastructure.adapter.persistence.repository")
public class ReactiveMongoConfiguration {
    public ReactiveMongoConfiguration() { log.info("Reactive Mongo Configuration INIT...."); }
}
