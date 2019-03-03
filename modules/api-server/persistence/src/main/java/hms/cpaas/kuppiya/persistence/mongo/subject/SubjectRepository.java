package hms.cpaas.kuppiya.persistence.mongo.subject;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SubjectRepository extends ReactiveMongoRepository<Subject, String> {
    Mono<Subject> findBySubjectCode(String subjectCode);
    Mono<Subject> findBySubjectId(String subjectId);
}
