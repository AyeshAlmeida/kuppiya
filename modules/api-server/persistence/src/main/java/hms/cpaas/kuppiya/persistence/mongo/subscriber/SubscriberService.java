package hms.cpaas.kuppiya.persistence.mongo.subscriber;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Mono;

public interface SubscriberService extends BaseService<Subscriber> {
    Mono<Subscriber> findBySubscriberId(String subscriberId);
    Mono<Subscriber> findByMaskedMsisdn(String maskedMsisdn);
    Mono<Subscriber> updateBySubscriberId(Subscriber entity, String subscriberId);
    Mono<Void> deleteBySubscriberId(String subscriberId);
}