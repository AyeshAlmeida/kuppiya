package hms.cpaas.kuppiya.persistence.mongo.subject;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface SubjectRepository extends ReactiveMongoRepository<Subject, String> {
    Mono<Subject> findBySubjectCode(String subjectCode);
    Mono<Subject> findBySubjectId(String subjectId);
    Flux<Subject> findSubjectBySubjectCodeIn(List<String> subjectCodes);
    Flux<Subject> findSubjectBySubjectIdIn(List<String> subjectIds);
}
