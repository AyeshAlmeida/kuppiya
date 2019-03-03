package hms.cpaas.kuppiya.persistence.mongo.location;

import hms.cpaas.kuppiya.persistence.mongo.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("location")
public class Location extends BaseEntity {
    @Indexed(unique = true)
    private String locationId;
    private String locationName;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("locationId='").append(locationId).append('\'');
        sb.append(", locationName='").append(locationName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
