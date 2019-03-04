package hms.cpaas.kuppiya.route;

import hms.cpaas.kuppiya.filter.RequestIdInjectionFilter;
import hms.cpaas.kuppiya.handler.InternalRouteHandler;
import hms.cpaas.kuppiya.util.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class InternalRoutes {
    @Autowired
    private RequestIdInjectionFilter requestIdInjectionFilter;

    @Bean
    public RouterFunction<ServerResponse> createLocation(InternalRouteHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/api/internal/location")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> handler.createLocation(RouterUtils.getRequestId(serverRequest), serverRequest))
                .filter(requestIdInjectionFilter);
    }

    @Bean
    public RouterFunction<ServerResponse> createSubject(InternalRouteHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/api/internal/subject")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> handler.createSubject(RouterUtils.getRequestId(serverRequest), serverRequest))
                .filter(requestIdInjectionFilter);
    }

    @Bean
    public RouterFunction<ServerResponse> createFaculty(InternalRouteHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/api/internal/faculty")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> handler.createFaculty(RouterUtils.getRequestId(serverRequest), serverRequest))
                .filter(requestIdInjectionFilter);
    }

    @Bean
    public RouterFunction<ServerResponse> createUniversity(InternalRouteHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/api/internal/university")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> handler.createUniversity(RouterUtils.getRequestId(serverRequest), serverRequest))
                .filter(requestIdInjectionFilter);
    }
}
