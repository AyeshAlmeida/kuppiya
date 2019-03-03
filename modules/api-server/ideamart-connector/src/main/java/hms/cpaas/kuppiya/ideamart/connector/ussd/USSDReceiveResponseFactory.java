package hms.cpaas.kuppiya.ideamart.connector.ussd;

import hms.cpaas.kuppiya.ideamart.connector.AbstractSDKBuilders;
import hms.cpaas.kuppiya.ideamart.connector.InvalidRequestExcpetion;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveResponse;

/**
 * USSDReceiveResponseFactory generates a USSD Receive Response with following format..
 * <pre>
 *     {
 *         "statusCode": "S1000",
 *         "statusDetail": "Success"
 *     }
 * </pre>
 */
public class USSDReceiveResponseFactory extends AbstractSDKBuilders {

    public USSDReceiveResponse create(String statusCode, String statusDetail) {
        if (statusCode == null) throw new InvalidRequestExcpetion("Status Code should not be null");
        if (statusDetail == null) throw new InvalidRequestExcpetion("Status Detail should not be null");

        USSDReceiveResponse ussdReceiveResponse = new USSDReceiveResponse();

        ussdReceiveResponse.setStatusCode(statusCode);
        ussdReceiveResponse.setStatusDetail(statusDetail);

        return ussdReceiveResponse;
    }
}
