package hms.cpaas.kuppiya.persistence.mongo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService<T> {
    Mono<T> findById(String id);
    Flux<T> findAll();
    Mono<T> create(T entity);
    Mono<T> update(T entity, String id);
}
