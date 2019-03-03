package hms.cpaas.kuppiya.ideamart.connector.sms;

import hms.cpaas.kuppiya.ideamart.connector.AbstractSDKBuilders;
import hms.cpaas.kuppiya.ideamart.connector.InvalidRequestExcpetion;
import hms.cpaas.kuppiya.ideamart.connector.sms.domain.SMSReportReceiptresponse;

/**
 * SMSReportReceiptResponseFactory generates a SMS Report Delivery Status Response with following format..
 * <pre>
 *     {
 *         "statusCode": "S1000",
 *         "statusDetail": "Success"
 *     }
 * </pre>
 */
public class SMSReportReceiptResponseFactory extends AbstractSDKBuilders {

    public SMSReportReceiptresponse create(String statusCode, String statusDetail) {
        if (statusCode == null) throw new InvalidRequestExcpetion("Status Code should not be null");
        if (statusDetail == null) throw new InvalidRequestExcpetion("Status Detail should not be null");

        SMSReportReceiptresponse smsReportReceiptresponse = new SMSReportReceiptresponse();

        smsReportReceiptresponse.setStatusCode(statusCode);
        smsReportReceiptresponse.setStatusDetail(statusDetail);

        return smsReportReceiptresponse;
    }
}
