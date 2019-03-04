package hms.cpaas.kuppiya.ideamart.connector.ussd.domain;

import hms.cpaas.kuppiya.ideamart.connector.Confirmation;

public class USSDSendConfirmation implements Confirmation {

    private String version;
    private String requestId;
    private String timeStamp;
    private String statusCode;
    private String statusDetail;

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @return the statusDetail
     */
    public String getStatusDetail() {
        return statusDetail;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("USSDSendConfirmation{");
        sb.append("version='").append(version).append('\'');
        sb.append(", requestId='").append(requestId).append('\'');
        sb.append(", timeStamp='").append(timeStamp).append('\'');
        sb.append(", statusCode='").append(statusCode).append('\'');
        sb.append(", statusDetail='").append(statusDetail).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
