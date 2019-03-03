package hms.cpaas.kuppiya.persistence.mongo.subject;

import hms.cpaas.kuppiya.persistence.mongo.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subject")
public class Subject extends BaseEntity {
    @Indexed(unique = true)
    private String subjectId;
    @Indexed(unique = true)
    private String subjectCode;
    private String subjectName;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subject{");
        sb.append("subjectCode='").append(subjectCode).append('\'');
        sb.append(", subjectName='").append(subjectName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
