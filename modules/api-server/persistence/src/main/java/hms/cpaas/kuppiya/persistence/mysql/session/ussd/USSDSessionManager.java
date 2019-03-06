package hms.cpaas.kuppiya.persistence.mysql.session.ussd;

import hms.cpaas.kuppiya.persistence.mysql.session.SessionManager;
import reactor.core.publisher.Mono;

public interface USSDSessionManager extends SessionManager<USSDSession> {
    Mono<USSDSession> findUSSDSessionBySessionIdAndMaskedMsisdn(String sessionId, String maskedMsisdn);
}
