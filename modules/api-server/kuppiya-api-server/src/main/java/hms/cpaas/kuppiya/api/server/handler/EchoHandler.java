package hms.cpaas.kuppiya.api.server.handler;

import hms.cpaas.kuppiya.api.messages.EchoResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class EchoHandler {
    public Mono<ServerResponse> handleEchoRequest(final ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new EchoResponse("success", "Hello, Spring Webflux Example!")));
    }
}
