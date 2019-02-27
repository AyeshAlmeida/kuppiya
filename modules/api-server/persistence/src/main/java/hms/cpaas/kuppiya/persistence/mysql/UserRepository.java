package hms.cpaas.kuppiya.persistence.mysql;

import hms.cpaas.kuppiya.api.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> save(User user);
    Mono<User> findUserById(long id);
    Mono<User> findUserByUserName(String userName);
    Flux<User> findAll();
}
