package hms.cpaas.kuppiya.persistence.mongo.location;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface LocationRepository extends ReactiveMongoRepository<Location, String> {
    Mono<Location> findByLocationId(String locationId);
    Flux<Location> findByLocationIdIn(List<String> locationIds);
    Mono<Void> deleteByLocationId(String locationId);
}
