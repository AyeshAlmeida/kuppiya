package hms.cpaas.kuppiya.ideamart.connector.ussd;

import hms.cpaas.kuppiya.ideamart.connector.MoListener;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveIndication;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveResponse;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendConfirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class USSDMoListener implements MoListener<USSDReceiveIndication, USSDReceiveResponse> {
    private static final Logger LOGGER = LoggerFactory.getLogger(USSDMoListener.class);

    @Override
    public Mono<USSDReceiveResponse> onReceived(USSDReceiveIndication moRequest) {
        LOGGER.info("Received USSD-MO Request[{}]", moRequest);
        if (Objects.nonNull(moRequest)) {
            USSDReceiveResponse response = new USSDReceiveResponse();
            response.setStatusCode("S1000");
            response.setStatusDetail("Successfully received the response");
            return Mono.just(response);
        } else {
            USSDReceiveResponse errorResponse = new USSDReceiveResponse();
            errorResponse.setStatusCode("E14011");
            errorResponse.setStatusDetail("Error occurred while handling USSD-MO Request [BAD_REQUEST]");
            return Mono.just(errorResponse);
        }
    }
}
