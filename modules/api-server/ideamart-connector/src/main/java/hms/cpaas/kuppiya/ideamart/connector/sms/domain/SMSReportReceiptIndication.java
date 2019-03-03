package hms.cpaas.kuppiya.ideamart.connector.sms.domain;

import hms.cpaas.kuppiya.ideamart.connector.Indication;
import hms.cpaas.kuppiya.ideamart.connector.sms.ReportDeliveryStaus;

public class SMSReportReceiptIndication implements Indication {
    private String destinationAddress;
    private String timestamp;
    private String requestId;
    private ReportDeliveryStaus deliveryStatus;

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timestamp;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @return the destinationAddress
     */
    public String getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * @return the deliveryStatus
     */
    public ReportDeliveryStaus getDeliveryStatus() {
        return deliveryStatus;
    }


}
