package hms.cpaas.kuppiya.ideamart.connector.sms;

import hms.cpaas.kuppiya.ideamart.connector.MoListener;
import hms.cpaas.kuppiya.ideamart.connector.sms.domain.SMSMOIndication;
import hms.cpaas.kuppiya.ideamart.connector.sms.domain.SMSMOResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SmsMoListener implements MoListener<SMSMOIndication, SMSMOResponse> {
    @Override
    public Mono<SMSMOResponse> onReceived(SMSMOIndication moRequest) {
        //todo: implement this method
        return null;
    }
}
