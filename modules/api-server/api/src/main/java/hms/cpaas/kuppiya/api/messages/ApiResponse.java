package hms.cpaas.kuppiya.api.messages;

public class ApiResponse {
    private String id;
    private String statusCode;
    private String description;

    public ApiResponse(String id, String statusCode, String description) {
        this.id = id;
        this.statusCode = statusCode;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", statusCode='").append(statusCode).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
