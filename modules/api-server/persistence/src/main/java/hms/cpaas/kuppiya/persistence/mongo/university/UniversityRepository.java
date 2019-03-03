package hms.cpaas.kuppiya.persistence.mongo.university;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UniversityRepository extends ReactiveMongoRepository<University, String> {
    Mono<University> findUniversitiesByUniversityCode(String universityCode);
    Mono<University> findByUniversityId(String universityId);
}
