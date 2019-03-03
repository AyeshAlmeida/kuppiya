package hms.cpaas.kuppiya.persistence.mongo.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    @Autowired
    private SubscriberRepository repository;

    @Override
    public Mono<Subscriber> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Subscriber> findBySubscriberId(String subscriberId) {
        return repository.findBySubscriberId(subscriberId);
    }

    @Override
    public Mono<Subscriber> findByMaskedMsisdn(String maskedMsisdn) {
        return repository.findByMaskedMsisdn(maskedMsisdn);
    }

    @Override
    public Flux<Subscriber> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Subscriber> create(Subscriber entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<Subscriber> update(Subscriber entity, String id) {
            return findById(id).doOnSuccess(foundSubscriber -> updateEntity(entity, foundSubscriber));
    }

    @Override
    public Mono<Subscriber> updateBySubscriberId(Subscriber entity, String subscriberId) {
        return findBySubscriberId(subscriberId).doOnSuccess(foundSubscriber -> updateEntity(entity, foundSubscriber));
    }

    private void updateEntity(Subscriber entity, Subscriber foundSubscriber) {
        foundSubscriber.setMaskedMsisdn(entity.getMaskedMsisdn());
        foundSubscriber.setUniversity(entity.getUniversity());
        repository.save(foundSubscriber).subscribe();
    }

    @Override
    public Mono<Void> deleteBySubscriberId(String subscriberId) {
        return repository.deleteBySubscriberId(subscriberId);
    }
}
