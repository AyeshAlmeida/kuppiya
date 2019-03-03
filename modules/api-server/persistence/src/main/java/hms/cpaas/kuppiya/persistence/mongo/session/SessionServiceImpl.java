package hms.cpaas.kuppiya.persistence.mongo.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository repository;

    @Override
    public Mono<Session> findBySessionId(String sessionId) {
        return repository.findBySessionId(sessionId);
    }

    @Override
    public Mono<Session> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Session> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<Session> findAllFinishedSessions() {
        return repository.findByFinished(true);
    }

    @Override
    public Flux<Session> findAllUnfinishedSessions() {
        return repository.findByFinished(false);
    }

    @Override
    public Mono<Session> create(Session entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<Session> update(Session entity, String id) {
        return findById(id).doOnSuccess(session -> updateEntity(entity, session));
    }

    @Override
    public Mono<Session> updateBySessionId(Session entity, String sessionId) {
        return findBySessionId(sessionId).doOnSuccess(session -> updateEntity(entity, session));
    }

    private void updateEntity(Session entity, Session session) {
        session.setSessionName(entity.getSessionName());
        session.setPlannedDateTime(entity.getPlannedDateTime());
        session.setFinished(entity.isFinished());
        session.setSessionCoordinator(entity.getSessionCoordinator());
        session.setSubscribers(entity.getSubscribers());
        session.setLocation(entity.getLocation());
        repository.save(session).subscribe();
    }
}
