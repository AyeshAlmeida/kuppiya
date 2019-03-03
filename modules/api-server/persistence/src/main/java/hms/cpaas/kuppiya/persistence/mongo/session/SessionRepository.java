package hms.cpaas.kuppiya.persistence.mongo.session;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SessionRepository extends ReactiveMongoRepository<Session, String> {
    Mono<Session> findBySessionId(String sessionId);
    Flux<Session> findByFinished(boolean isFinished);
}
