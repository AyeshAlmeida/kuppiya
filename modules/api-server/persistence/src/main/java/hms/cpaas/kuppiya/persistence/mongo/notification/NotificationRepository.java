package hms.cpaas.kuppiya.persistence.mongo.notification;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {
    Mono<Notification> findByNotificationId(String notificationId);
    Flux<Notification> findByStatus(NotificationStatus status);
    Flux<Notification> findByScheduledTimeBefore(LocalDateTime time);
    Mono<Void> deleteByNotificationId(String notificationId);
    Mono<Void> deleteByStatus(NotificationStatus status);
}
