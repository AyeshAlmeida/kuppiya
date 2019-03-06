package hms.cpaas.kuppiya.service;

import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveIndication;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveResponse;
import reactor.core.publisher.Mono;

public interface USSDRequestHandler {
    Mono<USSDReceiveResponse> handleUSSDFlow(final String requestId, final USSDReceiveIndication indication);
}
