package hms.cpaas.kuppiya.persistence.mongo.faculty;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface FacultyRepository extends ReactiveMongoRepository<Faculty, String> {
    Mono<Faculty> findByFacultyId(String facultyId);
    Mono<Faculty> findByFacultyCode(String facultyCode);
    Flux<Faculty> findByFacultyIdIn(List<String> facultyIds);
}
