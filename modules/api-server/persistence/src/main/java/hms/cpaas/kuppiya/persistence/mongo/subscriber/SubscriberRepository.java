package hms.cpaas.kuppiya.persistence.mongo.subscriber;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SubscriberRepository extends ReactiveMongoRepository<Subscriber, String> {
    Mono<Subscriber> findBySubscriberId(String subscriberId);
    Mono<Subscriber> findByMaskedMsisdn(String maskedMsisdn);
    Mono<Void> deleteBySubscriberId(String subscriberId);
}
