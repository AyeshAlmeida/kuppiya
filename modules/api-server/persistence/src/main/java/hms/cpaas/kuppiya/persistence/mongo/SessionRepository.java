package hms.cpaas.kuppiya.persistence.mongo;

import hms.cpaas.kuppiya.api.domain.Session;
import reactor.core.publisher.Mono;

public interface SessionRepository {
    Mono<Session> findSessionById(String id);
}
