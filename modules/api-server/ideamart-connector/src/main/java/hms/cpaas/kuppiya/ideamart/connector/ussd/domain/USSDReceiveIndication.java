package hms.cpaas.kuppiya.ideamart.connector.ussd.domain;

import hms.cpaas.kuppiya.ideamart.connector.Indication;
import hms.cpaas.kuppiya.ideamart.connector.MessageEncoding;
import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDOperation;

public class USSDReceiveIndication implements Indication {
    private String version;
    private String applicationId;
    private String message;
    private String requestId;
    private String sessionId;
    private USSDOperation ussdOperation;
    private String sourceAdress;
    private String vlrAddress;
    private MessageEncoding encoding;

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
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @return the ussdOperation
     */
    public USSDOperation getUssdOperation() {
        return ussdOperation;
    }

    /**
     * @return the vlrAddress
     */
    public String getVlrAddress() {
        return vlrAddress;
    }

    /**
     * @return the encoding
     */
    public MessageEncoding getEncoding() {
        return encoding;
    }

    /**
     * @return the sourceAdress
     */
    public String getSourceAdress() {
        return sourceAdress;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("USSDReceiveIndication{");
        sb.append("version='").append(version).append('\'');
        sb.append(", applicationId='").append(applicationId).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", requestId='").append(requestId).append('\'');
        sb.append(", sessionId='").append(sessionId).append('\'');
        sb.append(", ussdOperation=").append(ussdOperation);
        sb.append(", sourceAdress='").append(sourceAdress).append('\'');
        sb.append(", vlrAddress='").append(vlrAddress).append('\'');
        sb.append(", encoding=").append(encoding);
        sb.append('}');
        return sb.toString();
    }
}
