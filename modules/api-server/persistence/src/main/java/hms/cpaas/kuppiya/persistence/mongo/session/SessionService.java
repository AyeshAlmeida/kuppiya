package hms.cpaas.kuppiya.persistence.mongo.session;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SessionService extends BaseService<Session> {
    Mono<Session> findBySessionId(String sessionId);
    Flux<Session> findAllFinishedSessions();
    Flux<Session> findAllUnfinishedSessions();
    Mono<Session> updateBySessionId(Session session, String sessionId);
}
