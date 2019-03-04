package hms.cpaas.kuppiya.persistence.mongo.subject;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SubjectService extends BaseService<Subject> {
    Mono<Subject> findBySubjectId(String subjectId);
    Mono<Subject> findBySubjectCode(String subjectCode);
    Mono<Subject> updateBySubjectId(Subject entity, String subjectId);
    Mono<Subject> updateBySubjectCode(Subject entity, String subjectCode);
    Flux<Subject> findBySubjectIds(List<String> subjectIds);
}
