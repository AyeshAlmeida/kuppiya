package hms.cpaas.kuppiya.persistence.mongo.session;

import hms.cpaas.kuppiya.persistence.mongo.BaseEntity;
import hms.cpaas.kuppiya.persistence.mongo.location.Location;
import hms.cpaas.kuppiya.persistence.mongo.subscriber.Subscriber;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("session")
public class Session extends BaseEntity {
    @Indexed(unique = true)
    private String sessionId;
    private String sessionName;
    private LocalDateTime plannedDateTime;
    @DBRef
    private List<Subscriber> subscribers;
    @DBRef
    private Location location;
    private boolean finished;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public LocalDateTime getPlannedDateTime() {
        return plannedDateTime;
    }

    public void setPlannedDateTime(LocalDateTime plannedDateTime) {
        this.plannedDateTime = plannedDateTime;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Session{");
        sb.append("sessionId='").append(sessionId).append('\'');
        sb.append(", sessionName='").append(sessionName).append('\'');
        sb.append(", plannedDateTime=").append(plannedDateTime);
        sb.append(", subscribers=").append(subscribers);
        sb.append(", location=").append(location);
        sb.append(", finished=").append(finished);
        sb.append('}');
        return sb.toString();
    }
}
