package hms.cpaas.kuppiya.persistence.mongo.faculty;

import hms.cpaas.kuppiya.persistence.mongo.BaseEntity;
import hms.cpaas.kuppiya.persistence.mongo.subject.Subject;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("faculty")
public class Faculty extends BaseEntity {
    @Indexed(unique = true)
    private String facultyId;
    @Indexed(unique = true)
    private String facultyCode;
    private String facultyName;
    private String facultyDescription;
    @DBRef
    private List<Subject> subjects;

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyDescription() {
        return facultyDescription;
    }

    public void setFacultyDescription(String facultyDescription) {
        this.facultyDescription = facultyDescription;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Faculty{");
        sb.append("facultyId='").append(facultyId).append('\'');
        sb.append(", facultyCode='").append(facultyCode).append('\'');
        sb.append(", facultyName='").append(facultyName).append('\'');
        sb.append(", facultyDescription='").append(facultyDescription).append('\'');
        sb.append(", subjects=").append(subjects);
        sb.append('}');
        return sb.toString();
    }
}
