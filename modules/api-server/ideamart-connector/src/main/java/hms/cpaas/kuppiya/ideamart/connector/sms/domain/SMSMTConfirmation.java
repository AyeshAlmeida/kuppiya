package hms.cpaas.kuppiya.ideamart.connector.sms.domain;

import hms.cpaas.kuppiya.ideamart.connector.Confirmation;
import hms.cpaas.kuppiya.ideamart.connector.StatusCode;

import java.util.List;

public class SMSMTConfirmation implements Confirmation {

    private String version;
    private String requestId;
    private List<DestinationResponse> destinationResponses;
    private StatusCode statusCode;
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
     * @return the destinationResponses
     */
    public List<DestinationResponse> getDestinationResponses() {
        return destinationResponses;
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
