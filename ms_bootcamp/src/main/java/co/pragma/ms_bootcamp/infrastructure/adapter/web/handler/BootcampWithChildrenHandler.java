package co.pragma.ms_bootcamp.infrastructure.adapter.web.handler;

import co.pragma.ms_bootcamp.domain.enums.BootcampResponseMessage;
import co.pragma.ms_bootcamp.domain.model.BootcampWithChildren;
import co.pragma.ms_bootcamp.domain.port.input.BootcampPort;
import co.pragma.ms_bootcamp.infrastructure.utils.AbstractOutputObjectApi;
import co.pragma.ms_bootcamp.infrastructure.utils.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampWithChildrenHandler extends AbstractOutputObjectApi<PagedResponse<BootcampWithChildren>> {
    private final BootcampPort bootcampPort;

    public Mono<ServerResponse> getAllBootcamps(ServerRequest request) {
        int page = Integer.parseInt(request.queryParam("page").orElse("0"));
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        String sort = request.queryParam("sort").orElse("asc");
        String sortBy = request.queryParam("sortBy").orElse("name");

        return bootcampPort.countAllBootcamps()
                .flatMap(total -> {
                    int totalPages = (int) Math.ceil((double) total / size);

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(
                                    bootcampPort.getAllBootcamps(page, size, sort, sortBy)
                                            .collectList()
                                            .map(items -> createOutputObjectApi(
                                                    PagedResponse.<BootcampWithChildren>builder()
                                                            .pageSize(size)
                                                            .totalPages(totalPages)
                                                            .currentPage(page)
                                                            .totalElements(total.intValue())
                                                            .items(items)
                                                            .build(),
                                                    HttpStatus.OK.value(),
                                                    BootcampResponseMessage.BOOTCAMP_QUERY.getMessage()
                                            ))
                            );
                });
    }

}
