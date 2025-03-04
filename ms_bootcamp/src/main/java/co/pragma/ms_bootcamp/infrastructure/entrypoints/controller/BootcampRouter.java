package co.pragma.ms_bootcamp.infrastructure.entrypoints.controller;

import co.pragma.ms_bootcamp.domain.model.dto.BootcampRequest;
import co.pragma.ms_bootcamp.infrastructure.entrypoints.handler.BootcampHandler;
import co.pragma.ms_bootcamp.infrastructure.entrypoints.handler.BootcampWithChildrenHandler;
import co.pragma.ms_bootcamp.infrastructure.utils.OutputObjectApi;
import co.pragma.ms_bootcamp.infrastructure.utils.constants.ApiPaths;
import co.pragma.ms_bootcamp.infrastructure.utils.swagger.example.GetAllBootcamsExampleResponse;
import co.pragma.ms_bootcamp.infrastructure.utils.swagger.example.SaveBootcampExampleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BootcampRouter {

    @RouterOperations({
            @RouterOperation(
                    path = ApiPaths.SAVE_BOOTCAMP,
                    beanClass = BootcampHandler.class,
                    beanMethod = "saveBootcamp",
                    method = {RequestMethod.POST},
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    operation = @Operation(
                            operationId = "saveBootcamp",
                            summary = "Guardar un bootcamp",
                            description = "Crea un nuevo bootcamp y lo guarda en la base de datos.",
                            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = BootcampRequest.class))),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Successfully created",
                                            content = @Content(schema = @Schema(implementation = SaveBootcampExampleResponse.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Bad Request",
                                            content = @Content(schema = @Schema(implementation = OutputObjectApi.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "Not Found",
                                            content = @Content(schema = @Schema(implementation = OutputObjectApi.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "409",
                                            description = "Conflict",
                                            content = @Content(schema = @Schema(implementation = OutputObjectApi.class))
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = ApiPaths.GET_ALL_BOOTCAMPS,
                    method = {RequestMethod.GET},
                    beanClass = BootcampWithChildrenHandler.class,
                    beanMethod = "getAllBootcamps",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    operation = @Operation(
                            operationId = "getAllBootcamps",
                            summary = "Obtener todos los bootcamps",
                            description = "Devuelve una lista paginada de bootcamps.",
                            parameters = {
                                    @Parameter(
                                            name = "page",
                                            description = "Número de página",
                                            example = "0",
                                            schema = @Schema(type = "integer", format = "int32", defaultValue = "0")
                                    ),
                                    @Parameter(
                                            name = "size",
                                            description = "Tamaño de página",
                                            example = "10",
                                            schema = @Schema(type = "integer", format = "int32", defaultValue = "10")
                                    ),
                                    @Parameter(
                                            name = "sort",
                                            description = "Orden",
                                            example = "asc",
                                            schema = @Schema(type = "string", defaultValue = "asc", allowableValues = {"asc", "desc"})
                                    ),
                                    @Parameter(
                                            name = "sortBy",
                                            description = "Campo por el cual ordenar",
                                            example = "name",
                                            schema = @Schema(type = "string", defaultValue = "name", allowableValues = {"name", "capacitiesCount"})
                                    )
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Lista de bootcamps recuperada",
                                            content = @Content(schema = @Schema(implementation = GetAllBootcamsExampleResponse.class))
                                    ),
                                    @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                                            content = @Content(schema = @Schema(implementation = OutputObjectApi.class))
                                    )
                            }
                    )
            )
    })
    @Bean
    public RouterFunction<ServerResponse> bootcampRoutes(BootcampHandler bootcampHandler, BootcampWithChildrenHandler bootcampWithChildrenHandler) {
        return RouterFunctions.route()
                .POST(ApiPaths.SAVE_BOOTCAMP, bootcampHandler::saveBootcamp)
                .GET(ApiPaths.GET_ALL_BOOTCAMPS, bootcampWithChildrenHandler::getAllBootcamps)
                .build();
    }
}