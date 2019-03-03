package hms.cpaas.kuppiya.persistence.mongo.appUser;

import hms.cpaas.kuppiya.persistence.mongo.faculty.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository repository;

    @Override
    public Mono<AppUser> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<AppUser> findBySubscriberId(String subscriberId) {
        return repository.findBySubscriberId(subscriberId);
    }

    @Override
    public Mono<AppUser> findByMaskedMsisdn(String maskedMsisdn) {
        return repository.findByMaskedMsisdn(maskedMsisdn);
    }

    @Override
    public Flux<AppUser> findByFaculty(Faculty faculty) {
        return repository.findByFaculty(faculty);
    }

    @Override
    public Flux<AppUser> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<AppUser> create(AppUser entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<AppUser> update(AppUser entity, String id) {
            return findById(id).doOnSuccess(foundAppUser -> updateEntity(entity, foundAppUser));
    }

    @Override
    public Mono<AppUser> updateBySubscriberId(AppUser entity, String subscriberId) {
        return findBySubscriberId(subscriberId).doOnSuccess(foundAppUser -> updateEntity(entity, foundAppUser));
    }

    private void updateEntity(AppUser entity, AppUser foundAppUser) {
        foundAppUser.setMaskedMsisdn(entity.getMaskedMsisdn());
        foundAppUser.setUniversity(entity.getUniversity());
        foundAppUser.setFaculty(entity.getFaculty());
        repository.save(foundAppUser).subscribe();
    }

    @Override
    public Mono<Void> deleteBySubscriberId(String subscriberId) {
        return repository.deleteBySubscriberId(subscriberId);
    }
}
