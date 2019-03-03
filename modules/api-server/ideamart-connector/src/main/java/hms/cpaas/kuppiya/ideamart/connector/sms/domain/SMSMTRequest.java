package hms.cpaas.kuppiya.ideamart.connector.sms.domain;

import hms.cpaas.kuppiya.ideamart.connector.Request;

import java.util.List;

public class SMSMTRequest implements Request {
    private String message;
    private String applicationId;
    private String password;
    private String version;
    private List<String> destinationAddresses;
    private String sourceAddress;
    private String delivaryStatusRequest;
    private String encoding;
    private String binaryHeader;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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
     * @return the destinationAddresses
     */
    public List<String> getDestinationAddresses() {
        return destinationAddresses;
    }

    /**
     * @param destinationAddresses the destinationAddresses to set
     */
    public void setDestinationAddresses(List<String> destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    /**
     * @return the sourceAddress
     */
    public String getSourceAddress() {
        return sourceAddress;
    }

    /**
     * @param sourceAddress the sourceAddress to set
     */
    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    /**
     * @return the delivaryStatusRequest
     */
    public String getDelivaryStatusRequest() {
        return delivaryStatusRequest;
    }

    /**
     * @param delivaryStatusRequest the delivaryStatusRequest to set
     */
    public void setDelivaryStatusRequest(String delivaryStatusRequest) {
        this.delivaryStatusRequest = delivaryStatusRequest;
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * @return the binaryHeader
     */
    public String getBinaryHeader() {
        return binaryHeader;
    }

    /**
     * @param binaryHeader the binaryHeader to set
     */
    public void setBinaryHeader(String binaryHeader) {
        this.binaryHeader = binaryHeader;
    }
}
