package hms.cpaas.kuppiya.api.domain;

public class SubjectObject {
    private String subjectId;
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
        final StringBuilder sb = new StringBuilder("SubjectObject{");
        sb.append("subjectId='").append(subjectId).append('\'');
        sb.append(", subjectCode='").append(subjectCode).append('\'');
        sb.append(", subjectName='").append(subjectName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
