package hms.cpaas.kuppiya.persistence.mongo.notification;

import hms.cpaas.kuppiya.persistence.mongo.BaseEntity;
import hms.cpaas.kuppiya.persistence.mongo.session.Session;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("notification")
public class Notification extends BaseEntity {
    @Indexed(unique = true)
    private String notificationId;
    private NotificationStatus status;
    private LocalDateTime scheduledTime;
    @DBRef
    private Session session;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Notification{");
        sb.append("notificationId='").append(notificationId).append('\'');
        sb.append(", status=").append(status);
        sb.append(", session=").append(session);
        sb.append('}');
        return sb.toString();
    }
}
