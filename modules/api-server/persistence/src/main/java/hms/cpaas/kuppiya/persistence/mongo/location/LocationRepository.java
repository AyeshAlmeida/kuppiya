package hms.cpaas.kuppiya.persistence.mongo.location;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LocationRepository extends ReactiveMongoRepository<Location, String> {
    Mono<Location> findByLocationId(String locationId);
    Mono<Void> deleteByLocationId(String locationId);
}
