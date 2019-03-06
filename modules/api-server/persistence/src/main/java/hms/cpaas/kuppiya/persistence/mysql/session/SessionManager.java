package hms.cpaas.kuppiya.persistence.mysql.session;

import hms.cpaas.kuppiya.api.domain.ApiLevelSession;
import reactor.core.publisher.Mono;

public interface SessionManager<T extends ApiLevelSession> {
    Mono<T> createSession(T session);
    Mono<T> updateSession(T session);
}
