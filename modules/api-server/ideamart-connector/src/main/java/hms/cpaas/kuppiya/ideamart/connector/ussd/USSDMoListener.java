package hms.cpaas.kuppiya.ideamart.connector.ussd;

import hms.cpaas.kuppiya.ideamart.connector.MoListener;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveIndication;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class USSDMoListener implements MoListener<USSDReceiveIndication, USSDReceiveResponse> {

    @Override
    public Mono<USSDReceiveResponse> onReceived(USSDReceiveIndication moRequest) {
        // todo: implement this method
        return null;
    }
}
