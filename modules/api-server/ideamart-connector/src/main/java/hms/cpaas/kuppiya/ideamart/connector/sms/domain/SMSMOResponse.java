package hms.cpaas.kuppiya.ideamart.connector.sms.domain;

import hms.cpaas.kuppiya.ideamart.connector.Response;

public class SMSMOResponse implements Response {
    private String statusCode;
    private String statusDetail;

    /**
     * @return the statusCode
     */
    //only use for testing purposes
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the statusDetail
     */
    //only use for testing purposes
    public String getStatusDetail() {
        return statusDetail;
    }

    /**
     * @param statusDetail the statusDetail to set
     */
    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }
}
