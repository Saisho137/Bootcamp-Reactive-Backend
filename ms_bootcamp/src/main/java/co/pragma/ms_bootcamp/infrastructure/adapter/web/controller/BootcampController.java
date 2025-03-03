package co.pragma.ms_bootcamp.infrastructure.adapter.web.controller;

import co.pragma.ms_bootcamp.infrastructure.adapter.web.handler.BootcampHandler;
import co.pragma.ms_bootcamp.infrastructure.adapter.web.handler.BootcampWithChildrenHandler;
import co.pragma.ms_bootcamp.infrastructure.utils.constants.ApiPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BootcampController {

    @Bean
    public RouterFunction<ServerResponse> bootcampRoutes(BootcampHandler bootcampHandler, BootcampWithChildrenHandler bootcampWithChildrenHandler) {
        return RouterFunctions.route()
                .POST(ApiPaths.SAVE_BOOTCAMP, bootcampHandler::saveBootcamp)
                .GET(ApiPaths.GET_ALL_BOOTCAMPS, bootcampWithChildrenHandler::getAllBootcamps)
                .build();
    }
}