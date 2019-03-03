package hms.cpaas.kuppiya.ideamart.connector.sms;

import hms.cpaas.kuppiya.ideamart.connector.AbstractSDKBuilders;
import hms.cpaas.kuppiya.ideamart.connector.InvalidRequestExcpetion;
import hms.cpaas.kuppiya.ideamart.connector.sms.domain.SMSMOResponse;

/**
 * SmsMOResponseFactory generates a SMS Mobile Originated Response with following format..
 * <pre>
 *     {
 *         "statusCode": "S1000",
 *         "statusDetail": "Success"
 *     }
 * </pre>
 */
public class SMSMOResponseFactory extends AbstractSDKBuilders {

    public SMSMOResponse create(String statusCode, String statusDetail) {
        if (statusCode == null) throw new InvalidRequestExcpetion("Status Code should not be null");
        if (statusDetail == null) throw new InvalidRequestExcpetion("Status Detail should not be null");

        SMSMOResponse smsMoResponse = new SMSMOResponse();

        smsMoResponse.setStatusCode(statusCode);
        smsMoResponse.setStatusDetail(statusDetail);

        return smsMoResponse;
    }
}
