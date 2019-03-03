package hms.cpaas.kuppiya.persistence.mongo.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository repository;

    @Override
    public Mono<Location> findByLocationId(String locationId) {
        return repository.findByLocationId(locationId);
    }

    @Override
    public Mono<Location> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Location> create(Location entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<Location> update(Location entity, String id) {
        return findById(id).doOnSuccess(location -> updateEntity(entity, location));
    }

    @Override
    public Mono<Location> updateByLocationId(Location entity, String locationId) {
        return findByLocationId(locationId).doOnSuccess(location -> updateEntity(entity, location));
    }

    private void updateEntity(Location entity, Location location) {
        location.setLocationName(entity.getLocationName());
        repository.save(location).subscribe();
    }

    @Override
    public Mono<Void> deleteByLocationId(String locationId) {
        return repository.deleteByLocationId(locationId);
    }
}
