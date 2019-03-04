package hms.cpaas.kuppiya.api.domain;

import java.util.List;

public class FacultyObject {
    private String facultyId;
    private String facultyCode;
    private String facultyName;
    private String facultyDescription;
    private List<String> subjects;

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

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FacultyObject{");
        sb.append("facultyId='").append(facultyId).append('\'');
        sb.append(", facultyCode='").append(facultyCode).append('\'');
        sb.append(", facultyName='").append(facultyName).append('\'');
        sb.append(", facultyDescription='").append(facultyDescription).append('\'');
        sb.append(", subjects=").append(subjects);
        sb.append('}');
        return sb.toString();
    }
}
