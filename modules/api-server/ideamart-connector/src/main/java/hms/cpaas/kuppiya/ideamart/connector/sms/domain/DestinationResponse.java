package hms.cpaas.kuppiya.ideamart.connector.sms.domain;

import hms.cpaas.kuppiya.ideamart.connector.Response;
import hms.cpaas.kuppiya.ideamart.connector.StatusCode;

public class DestinationResponse implements Response {

    private String timestamp;
    private String address;
    private String messageId;
    private StatusCode statusCode;
    private String statusDetail;

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @return the statusCode
     */
    public StatusCode getStatusCode() {
        return statusCode;
    }

    /**
     * @return the statusDetail
     */
    public String getStatusDetail() {
        return statusDetail;
    }
}
