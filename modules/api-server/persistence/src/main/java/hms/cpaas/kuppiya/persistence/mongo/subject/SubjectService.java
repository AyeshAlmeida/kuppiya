package hms.cpaas.kuppiya.persistence.mongo.subject;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Mono;

public interface SubjectService extends BaseService<Subject> {
    Mono<Subject> findBySubjectId(String subjectId);
    Mono<Subject> findBySubjectCode(String subjectCode);
    Mono<Subject> updateBySubjectId(Subject entity, String subjectId);
    Mono<Subject> updateBySubjectCode(Subject entity, String subjectCode);
}
