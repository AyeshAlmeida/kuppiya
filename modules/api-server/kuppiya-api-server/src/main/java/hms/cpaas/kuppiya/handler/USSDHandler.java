package hms.cpaas.kuppiya.handler;

import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDApi;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveIndication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class USSDHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(USSDHandler.class);

    public Mono<ServerResponse> handleUSSDMO(final ServerRequest request) {
        //todo: implement this method
        return request
                .bodyToMono(String.class)
//                .map(USSDApi::ussdReceiveIndication)
                .doOnNext(req -> LOGGER.info("Received Ussd Mo Request [{}]", req))
                .map(req -> USSDApi.newReceiveUSSD("S1000", "SUCCESS"))
                .flatMap(req -> ServerResponse.ok().build());
    }
}
