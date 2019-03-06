package hms.cpaas.kuppiya.route;

import hms.cpaas.kuppiya.filter.RequestIdInjectionFilter;
import hms.cpaas.kuppiya.handler.SMSHandler;
import hms.cpaas.kuppiya.handler.USSDHandler;
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
public class PlatformRoute {
    @Autowired
    private RequestIdInjectionFilter requestIdInjectionFilter;

    @Bean
    public RouterFunction<ServerResponse> USSDMORequest(USSDHandler ussdHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/api/platform/ussd/mo")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> ussdHandler.handleUSSDMO(RouterUtils.getRequestId(serverRequest), serverRequest))
                .filter(requestIdInjectionFilter);
    }

    @Bean
    public RouterFunction<ServerResponse> SCHMORequest(SMSHandler smsHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/api/platform/sms/mo")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> smsHandler.handleSMSMO(RouterUtils.getRequestId(serverRequest), serverRequest))
                .filter(requestIdInjectionFilter);
    }
}
