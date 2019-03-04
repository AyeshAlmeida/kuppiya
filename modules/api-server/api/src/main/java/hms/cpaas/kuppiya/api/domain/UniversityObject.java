package hms.cpaas.kuppiya.api.domain;

import java.util.List;

public class UniversityObject {
    private String universityId;
    private String universityCode;
    private String name;
    private List<String> faculties;
    private List<String> locations;

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<String> faculties) {
        this.faculties = faculties;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UniversityObject{");
        sb.append("universityId='").append(universityId).append('\'');
        sb.append(", universityCode='").append(universityCode).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", faculties=").append(faculties);
        sb.append(", locations=").append(locations);
        sb.append('}');
        return sb.toString();
    }
}
