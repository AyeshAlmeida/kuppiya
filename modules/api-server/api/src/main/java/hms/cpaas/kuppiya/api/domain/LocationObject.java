package hms.cpaas.kuppiya.api.domain;

public class LocationObject {
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
        final StringBuilder sb = new StringBuilder("LocationObject{");
        sb.append("locationId='").append(locationId).append('\'');
        sb.append(", locationName='").append(locationName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
