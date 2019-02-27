package hms.cpaas.kuppiya.api.server.route;

import hms.cpaas.kuppiya.api.server.handler.EchoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class EchoRoutes {
    @Bean
    public RouterFunction<ServerResponse> routeEcho(EchoHandler echoHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/api/echo")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        echoHandler::handleEchoRequest);

    }
}
