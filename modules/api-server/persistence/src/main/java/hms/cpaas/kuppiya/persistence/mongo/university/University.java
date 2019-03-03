package hms.cpaas.kuppiya.persistence.mongo.university;
import hms.cpaas.kuppiya.persistence.mongo.BaseEntity;
import hms.cpaas.kuppiya.persistence.mongo.faculty.Faculty;
import hms.cpaas.kuppiya.persistence.mongo.location.Location;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "university")
public class University extends BaseEntity {
    @Indexed(unique = true)
    private String universityId;
    @Indexed(unique = true)
    private String universityCode;
    private String name;
    @DBRef
    private List<Faculty> faculties;
    @DBRef
    private List<Location> locations;

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("University{");
        sb.append("universityId='").append(universityId).append('\'');
        sb.append(", universityCode='").append(universityCode).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", faculties=").append(faculties);
        sb.append(", locations=").append(locations);
        sb.append('}');
        return sb.toString();
    }
}
