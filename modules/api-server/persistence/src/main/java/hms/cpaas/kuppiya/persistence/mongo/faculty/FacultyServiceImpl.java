package hms.cpaas.kuppiya.persistence.mongo.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyRepository repository;

    @Override
    public Mono<Faculty> findByFacultyId(String facultyId) {
        return repository.findByFacultyId(facultyId);
    }

    @Override
    public Mono<Faculty> findByFacultyCode(String facultyCode) {
        return repository.findByFacultyCode(facultyCode);
    }

    @Override
    public Mono<Faculty> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Faculty> findByFacultyIdIn(List<String> facultyIds) {
        return repository.findByFacultyIdIn(facultyIds);
    }

    @Override
    public Flux<Faculty> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Faculty> create(Faculty entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<Faculty> update(Faculty entity, String id) {
        return findById(id).doOnSuccess(faculty -> updateEntity(entity, faculty));
    }

    @Override
    public Mono<Faculty> updateByFacultyId(Faculty entity, String facultyId) {
        return findByFacultyId(facultyId).doOnSuccess(faculty -> updateEntity(entity, faculty));
    }

    @Override
    public Mono<Faculty> updateByFacultyFacultyCode(Faculty entity, String facultyCode) {
        return findByFacultyCode(facultyCode).doOnSuccess(faculty -> updateEntity(entity, faculty));
    }

    private void updateEntity(Faculty entity, Faculty faculty) {
        faculty.setFacultyName(entity.getFacultyName());
        faculty.setFacultyDescription(entity.getFacultyDescription());
        faculty.setSubjects(entity.getSubjects());
        repository.save(faculty).subscribe();
    }
}
