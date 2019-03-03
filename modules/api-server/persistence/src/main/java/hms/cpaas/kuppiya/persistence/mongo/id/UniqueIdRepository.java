package hms.cpaas.kuppiya.persistence.mongo.id;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UniqueIdRepository extends ReactiveMongoRepository<UniqueId, String> {
    Mono<UniqueId> findByName(String name);
}
