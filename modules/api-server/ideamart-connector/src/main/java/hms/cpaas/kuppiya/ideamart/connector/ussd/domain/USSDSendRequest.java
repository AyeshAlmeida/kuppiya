package hms.cpaas.kuppiya.ideamart.connector.ussd.domain;

import hms.cpaas.kuppiya.ideamart.connector.MessageEncoding;
import hms.cpaas.kuppiya.ideamart.connector.Request;
import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDOperation;

public class USSDSendRequest implements Request {

    private String version;
    private String applicationId;
    private String password;
    private String sessionId;
    private USSDOperation ussdOperation;
    private String destinationAddress;
    private MessageEncoding encoding;

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the ussdOperation
     */
    public USSDOperation getUssdOperation() {
        return ussdOperation;
    }

    /**
     * @param ussdOperation the ussdOperation to set
     */
    public void setUssdOperation(USSDOperation ussdOperation) {
        this.ussdOperation = ussdOperation;
    }

    /**
     * @return the destinationAddress
     */
    public String getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * @param destinationAddress the destinationAddress to set
     */
    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * @return the encoding
     */
    public MessageEncoding getEncoding() {
        return encoding;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(MessageEncoding encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("USSDSendRequest{");
        sb.append("version='").append(version).append('\'');
        sb.append(", applicationId='").append(applicationId).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", sessionId='").append(sessionId).append('\'');
        sb.append(", ussdOperation=").append(ussdOperation);
        sb.append(", destinationAddress='").append(destinationAddress).append('\'');
        sb.append(", encoding=").append(encoding);
        sb.append('}');
        return sb.toString();
    }
}
