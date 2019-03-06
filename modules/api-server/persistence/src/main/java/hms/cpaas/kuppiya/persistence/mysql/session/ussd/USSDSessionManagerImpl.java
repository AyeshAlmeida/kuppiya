package hms.cpaas.kuppiya.persistence.mysql.session.ussd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class USSDSessionManagerImpl implements USSDSessionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(USSDSessionManagerImpl.class);

    private final USSDSessionRepository repository;

    @Autowired
    public USSDSessionManagerImpl(USSDSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<USSDSession> findUSSDSessionBySessionIdAndMaskedMsisdn(String sessionId, String maskedMsisdn) {
        LOGGER.info("Finding USSDSession for [{}/{}]", sessionId, maskedMsisdn);
        return repository.findUSSDSessionBySessionIdAndMSISDN(sessionId, maskedMsisdn);
    }

    @Override
    public Mono<USSDSession> createSession(USSDSession session) {
        LOGGER.info("Saving USSDSession[{}]", session);
        return repository.createUSSDSession(session);
    }

    @Override
    public Mono<USSDSession> updateSession(USSDSession session) {
        LOGGER.info("Updating USSDSession[{}]", session);
        return repository.updateUSSDSession(session);
    }
}
