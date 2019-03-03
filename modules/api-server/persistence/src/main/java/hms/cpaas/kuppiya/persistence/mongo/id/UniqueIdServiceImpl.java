package hms.cpaas.kuppiya.persistence.mongo.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UniqueIdServiceImpl implements UniqueIdService {
    private static final String COLLECTION_NAME = "unique_id_collection";

    @Autowired
    private UniqueIdRepository repository;

    @Override
    public Mono<UniqueId> findUniqueIdByName() {
        return repository.findByName(COLLECTION_NAME);
    }

    @Override
    public Mono<UniqueId> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<UniqueId> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<UniqueId> create(UniqueId entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<UniqueId> update(UniqueId entity, String id) {
        return findById(id).doOnSuccess(uniqueId -> {
            uniqueId.setName(entity.getName());
            uniqueId.setNotificationId(entity.getNotificationId());
            uniqueId.setSessionId(entity.getNotificationId());
            uniqueId.setSubjectId(entity.getSubjectId());
            uniqueId.setUniversityId(entity.getUniversityId());
            uniqueId.setSubscriberId(entity.getSubscriberId());
            repository.save(uniqueId).subscribe();
        });
    }

    @Override
    public Mono<UniqueId> updateSubscriberId() {
        return findUniqueIdByName().doOnSuccess(uniqueId -> {
            long subscriberId = uniqueId.getSubscriberId();
            uniqueId.setSubscriberId(subscriberId+1);
            repository.save(uniqueId).subscribe();
        });
    }

    @Override
    public Mono<UniqueId> updateUniversityId() {
        return findUniqueIdByName().doOnSuccess(uniqueId -> {
            long universityId = uniqueId.getUniversityId();
            uniqueId.setUniversityId(universityId+1);
            repository.save(uniqueId).subscribe();
        });
    }

    @Override
    public Mono<UniqueId> updateSubjectId() {
        return findUniqueIdByName().doOnSuccess(uniqueId -> {
            long subjectId = uniqueId.getSubjectId();
            uniqueId.setSubjectId(subjectId+1);
            repository.save(uniqueId).subscribe();
        });
    }

    @Override
    public Mono<UniqueId> updateSessionId() {
        return findUniqueIdByName().doOnSuccess(uniqueId -> {
            long sessionId = uniqueId.getSessionId();
            uniqueId.setSessionId(sessionId+1);
            repository.save(uniqueId).subscribe();
        });
    }

    @Override
    public Mono<UniqueId> updateNotificationId() {
        return findUniqueIdByName().doOnSuccess(uniqueId -> {
            long notificationId = uniqueId.getNotificationId();
            uniqueId.setNotificationId(notificationId+1);
            repository.save(uniqueId).subscribe();
        });
    }
}
