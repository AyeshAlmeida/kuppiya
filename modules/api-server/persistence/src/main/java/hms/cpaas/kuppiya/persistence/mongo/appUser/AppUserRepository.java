package hms.cpaas.kuppiya.persistence.mongo.appUser;

import hms.cpaas.kuppiya.persistence.mongo.faculty.Faculty;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AppUserRepository extends ReactiveMongoRepository<AppUser, String> {
    Mono<AppUser> findBySubscriberId(String subscriberId);
    Mono<AppUser> findByMaskedMsisdn(String maskedMsisdn);
    Flux<AppUser> findByFaculty(Faculty faculty);
    Mono<Void> deleteBySubscriberId(String subscriberId);
}
