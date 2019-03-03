package hms.cpaas.kuppiya.persistence.mongo.location;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Mono;

public interface LocationService extends BaseService<Location> {
    Mono<Location> findByLocationId(String locationId);
    Mono<Location> updateByLocationId(Location entity, String locationId);
    Mono<Void> deleteByLocationId(String locationId);
}
