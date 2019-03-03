package hms.cpaas.kuppiya.ideamart.connector.ussd;

import hms.cpaas.kuppiya.ideamart.connector.MtExecutor;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendRequest;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendConfirmation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class USSDMtExecutor implements MtExecutor<USSDSendRequest, USSDSendConfirmation> {
    @Override
    public Mono<USSDSendConfirmation> sendRequest(USSDSendRequest mtRequest) {
        // todo: implement this method
        return null;
    }
}
