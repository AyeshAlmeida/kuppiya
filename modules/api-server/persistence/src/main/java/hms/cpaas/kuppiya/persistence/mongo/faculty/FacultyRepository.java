package hms.cpaas.kuppiya.persistence.mongo.faculty;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FacultyRepository extends ReactiveMongoRepository<Faculty, String> {
    Mono<Faculty> findByFacultyId(String facultyId);
    Mono<Faculty> findByFacultyCode(String facultyCode);
}
