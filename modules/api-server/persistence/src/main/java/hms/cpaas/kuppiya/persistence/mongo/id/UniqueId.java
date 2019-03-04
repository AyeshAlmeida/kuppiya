package hms.cpaas.kuppiya.persistence.mongo.id;

import hms.cpaas.kuppiya.persistence.mongo.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("unique_id")
public class UniqueId extends BaseEntity {
    @Indexed(unique = true)
    private String name;
    private long subscriberId;
    private long universityId;
    private long subjectId;
    private long sessionId;
    private long notificationId;
    private long facultyId;
    private long locationId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UniqueId{");
        sb.append("name='").append(name).append('\'');
        sb.append(", subscriberId=").append(subscriberId);
        sb.append(", universityId=").append(universityId);
        sb.append(", subjectId=").append(subjectId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", notificationId=").append(notificationId);
        sb.append(", facultyId=").append(facultyId);
        sb.append(", locationId=").append(locationId);
        sb.append('}');
        return sb.toString();
    }
}
