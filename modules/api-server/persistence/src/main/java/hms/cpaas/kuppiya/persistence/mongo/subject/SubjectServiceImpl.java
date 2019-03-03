package hms.cpaas.kuppiya.persistence.mongo.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository repository;

    @Override
    public Mono<Subject> findBySubjectId(String subjectId) {
        return repository.findBySubjectId(subjectId);
    }

    @Override
    public Mono<Subject> findBySubjectCode(String subjectCode) {
        return repository.findBySubjectCode(subjectCode);
    }

    @Override
    public Mono<Subject> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Subject> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Subject> create(Subject entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<Subject> update(Subject entity, String id) {
        return findById(id).doOnSuccess(subject -> updateEntity(entity, subject));
    }

    @Override
    public Mono<Subject> updateBySubjectId(Subject entity, String subjectId) {
        return findBySubjectId(subjectId).doOnSuccess(subject -> updateEntity(entity, subject));
    }

    @Override
    public Mono<Subject> updateBySubjectCode(Subject entity, String subjectCode) {
        return findBySubjectCode(subjectCode).doOnSuccess(subject -> updateEntity(entity, subject));
    }

    private void updateEntity(Subject entity, Subject subject) {
        subject.setSubjectCode(entity.getSubjectCode());
        subject.setSubjectName(entity.getSubjectName());
        repository.save(subject).subscribe();
    }
}
