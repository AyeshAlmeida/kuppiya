package hms.cpaas.kuppiya.persistence.mongo.id;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Mono;

public interface UniqueIdService extends BaseService<UniqueId> {
    Mono<UniqueId> findUniqueIdByName();
    Mono<UniqueId> updateSubscriberId();
    Mono<UniqueId> updateUniversityId();
    Mono<UniqueId> updateSubjectId();
    Mono<UniqueId> updateSessionId();
    Mono<UniqueId> updateNotificationId();
    Mono<UniqueId> updateFacultyId();
    Mono<UniqueId> updateLocationId();
}
