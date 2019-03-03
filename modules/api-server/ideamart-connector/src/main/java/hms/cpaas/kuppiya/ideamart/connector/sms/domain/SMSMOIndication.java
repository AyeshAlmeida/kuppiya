package hms.cpaas.kuppiya.ideamart.connector.sms.domain;

import hms.cpaas.kuppiya.ideamart.connector.Indication;
import hms.cpaas.kuppiya.ideamart.connector.MessageEncoding;

public class SMSMOIndication implements Indication {

    private String version;
    private String applicationId;
    private String sourceAddress;
    private String message;
    private String requestId;
    private MessageEncoding messageEncoding;

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @return the sourceAddress
     */
    public String getSourceAddress() {
        return sourceAddress;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @return the messageEncoding
     */
    public MessageEncoding getMessageEncoding() {
        return messageEncoding;
    }
}
