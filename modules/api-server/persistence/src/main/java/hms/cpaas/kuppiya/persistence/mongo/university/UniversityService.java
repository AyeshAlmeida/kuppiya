package hms.cpaas.kuppiya.persistence.mongo.university;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Mono;

public interface UniversityService extends BaseService<University> {
    Mono<University> findByUniversityCode(String universityCode);
    Mono<University> findByUniversityId(String universityId);
    Mono<University> updateByUniversityId(University entity, String universityId);
    Mono<University> updateByUniversityCode(University entity, String universityCode);
}
