package hms.cpaas.kuppiya.persistence.mongo.appUser;

import hms.cpaas.kuppiya.persistence.mongo.BaseEntity;
import hms.cpaas.kuppiya.persistence.mongo.faculty.Faculty;
import hms.cpaas.kuppiya.persistence.mongo.university.University;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "app_user")
public class AppUser extends BaseEntity {
    @Indexed(unique = true)
    private String subscriberId;
    @Indexed(unique = true)
    private String maskedMsisdn;
    @DBRef
    private University university;
    @DBRef
    private Faculty faculty;

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getMaskedMsisdn() {
        return maskedMsisdn;
    }

    public void setMaskedMsisdn(String maskedMsisdn) {
        this.maskedMsisdn = maskedMsisdn;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("subscriberId='").append(subscriberId).append('\'');
        sb.append(", maskedMsisdn='").append(maskedMsisdn).append('\'');
        sb.append(", university=").append(university);
        sb.append(", faculty=").append(faculty);
        sb.append('}');
        return sb.toString();
    }
}
