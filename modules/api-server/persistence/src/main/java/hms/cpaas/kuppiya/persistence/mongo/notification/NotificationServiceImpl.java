package hms.cpaas.kuppiya.persistence.mongo.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository repository;

    @Override
    public Mono<Notification> findByNotificationId(String notificationId) {
        return repository.findByNotificationId(notificationId);
    }

    @Override
    public Flux<Notification> findByNotificationStatus(NotificationStatus status) {
        return repository.findByStatus(status);
    }

    @Override
    public Flux<Notification> findNotificationsScheduledBefore(LocalDateTime time) {
        return repository.findByScheduledTimeBefore(time);
    }

    @Override
    public Mono<Notification> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Notification> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Notification> create(Notification entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<Notification> update(Notification entity, String id) {
        return findById(id).doOnSuccess(notification -> updateEntity(entity, notification));
    }

    @Override
    public Mono<Notification> updateNotificationByNotificationId(Notification entity, String notificationId) {
        return findByNotificationId(notificationId).doOnSuccess(notification -> updateEntity(entity, notification));
    }

    private void updateEntity(Notification entity, Notification notification) {
        notification.setStatus(entity.getStatus());
        repository.save(entity).subscribe();
    }

    @Override
    public Mono<Void> deleteByNotificationId(String notificationId) {
        return repository.deleteByNotificationId(notificationId);
    }

    @Override
    public Mono<Void> deleteByNotificationStatus(NotificationStatus status) {
        return repository.deleteByStatus(status);
    }
}
