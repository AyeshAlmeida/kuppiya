package hms.cpaas.kuppiya.persistence.mysql.session.ussd;

import reactor.core.publisher.Mono;

public interface USSDSessionRepository {
    Mono<USSDSession> createUSSDSession(final USSDSession session);
    Mono<USSDSession> findUSSDSessionBySessionIdAndMSISDN(final String sessionId, final String msisdn);
    Mono<USSDSession> updateUSSDSession(final USSDSession session);
}
