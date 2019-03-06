package hms.cpaas.kuppiya.handler;

import hms.cpaas.kuppiya.api.domain.USSDIndicationObject;
import hms.cpaas.kuppiya.ideamart.connector.MessageEncoding;
import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDApi;
import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDOperation;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveIndication;
import hms.cpaas.kuppiya.service.USSDRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class USSDHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(USSDHandler.class);

    private final USSDRequestHandler requestHandler;

    @Autowired
    public USSDHandler(USSDRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public Mono<ServerResponse> handleUSSDMO(final String requestId, final ServerRequest request) {
        return request
                .bodyToMono(USSDIndicationObject.class)
                .map(this::getReceiveIndicationFromObject)
                .doOnNext(req -> LOGGER.info("Received Ussd Mo Request [{}]", req))
                .flatMap(req -> requestHandler.handleUSSDFlow(requestId, req))
                .flatMap(req -> ServerResponse.ok().body(BodyInserters.fromObject(req)));
    }

    private USSDReceiveIndication getReceiveIndicationFromObject(USSDIndicationObject object) {
        return new USSDReceiveIndication(
                object.getVersion(), object.getApplicationId(), object.getMessage(), object.getRequestId(),
                object.getSessionId(), getUssdOperationFromString(object.getUssdOperation()),
                object.getSourceAddress(), object.getVlrAddress(),
                getMessageEncodingFromString(object.getEncoding())
        );
    }

    private USSDOperation getUssdOperationFromString(String operation) {
        for (USSDOperation op: USSDOperation.values()) {
            if (op.getValue().equalsIgnoreCase(operation)) {
                return op;
            }
        }
        return null;
    }

    private MessageEncoding getMessageEncodingFromString(String encoding) {
        for (MessageEncoding messageEncoding: MessageEncoding.values()) {
            if (messageEncoding.getValue().equalsIgnoreCase(encoding)) {
                return messageEncoding;
            }
        }
        return null;
    }
}
