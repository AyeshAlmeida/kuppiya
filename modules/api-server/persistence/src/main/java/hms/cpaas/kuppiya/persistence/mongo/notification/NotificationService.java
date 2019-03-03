package hms.cpaas.kuppiya.persistence.mongo.notification;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface NotificationService extends BaseService<Notification> {
    Mono<Notification> findByNotificationId(String notificationId);
    Flux<Notification> findByNotificationStatus(NotificationStatus status);
    Flux<Notification> findNotificationsScheduledBefore(LocalDateTime time);
    Mono<Notification> updateNotificationByNotificationId(Notification notification, String notificationId);
    Mono<Void> deleteByNotificationId(String notificationId);
    Mono<Void> deleteByNotificationStatus(NotificationStatus status);
}
