package hms.cpaas.kuppiya.persistence.mongo.appUser;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import hms.cpaas.kuppiya.persistence.mongo.faculty.Faculty;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AppUserService extends BaseService<AppUser> {
    Mono<AppUser> findBySubscriberId(String subscriberId);
    Mono<AppUser> findByMaskedMsisdn(String maskedMsisdn);
    Flux<AppUser> findByFaculty(Faculty faculty);
    Mono<AppUser> updateBySubscriberId(AppUser entity, String subscriberId);
    Mono<Void> deleteBySubscriberId(String subscriberId);
}