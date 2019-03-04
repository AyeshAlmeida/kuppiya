package hms.cpaas.kuppiya.persistence.mongo.location;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LocationService extends BaseService<Location> {
    Mono<Location> findByLocationId(String locationId);
    Flux<Location> findByLocationIdsIn(List<String> locationIds);
    Mono<Location> updateByLocationId(Location entity, String locationId);
    Mono<Void> deleteByLocationId(String locationId);
}
