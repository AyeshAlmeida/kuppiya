package hms.cpaas.kuppiya.persistence.mongo.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityRepository repository;

    @Override
    public Mono<University> findByUniversityCode(String universityCode) {
        return repository.findUniversitiesByUniversityCode(universityCode);
    }

    @Override
    public Mono<University> findByUniversityId(String universityId) {
        return repository.findByUniversityId(universityId);
    }

    @Override
    public Mono<University> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<University> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<University> create(University entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<University> update(University entity, String id) {
        return findById(id).doOnSuccess(university -> updateEntity(entity, university));
    }

    @Override
    public Mono<University> updateByUniversityId(University entity, String universityId) {
        return findByUniversityId(universityId).doOnSuccess(university -> updateEntity(entity, university));
    }

    @Override
    public Mono<University> updateByUniversityCode(University entity, String universityCode) {
        return findByUniversityCode(universityCode).doOnSuccess(university -> updateEntity(entity, university));
    }

    private void updateEntity(University entity, University university) {
        university.setName(entity.getName());
        university.setUniversityCode(entity.getUniversityCode());
        university.setFaculties(entity.getFaculties());
        university.setLocations(entity.getLocations());
        repository.save(university).subscribe();
    }
}
