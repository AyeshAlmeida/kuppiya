package hms.cpaas.kuppiya.ideamart.connector.sms;

import hms.cpaas.kuppiya.ideamart.connector.MtExecutor;
import hms.cpaas.kuppiya.ideamart.connector.sms.domain.SMSMTConfirmation;
import hms.cpaas.kuppiya.ideamart.connector.sms.domain.SMSMTRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SmsMtExecutor implements MtExecutor<SMSMTRequest, SMSMTConfirmation> {
    @Override
    public Mono<SMSMTConfirmation> sendRequest(SMSMTRequest mtRequest) {
        //todo: implement this method
        return null;
    }
}
