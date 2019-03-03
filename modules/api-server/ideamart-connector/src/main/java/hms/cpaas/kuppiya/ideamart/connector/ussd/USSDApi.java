package hms.cpaas.kuppiya.ideamart.connector.ussd;

import hms.cpaas.kuppiya.ideamart.connector.AbstractSDKBuilders;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveIndication;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveResponse;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendConfirmation;

/**
 * This is the main utility class for generating TAP USSD api related requests/responses/indications/confirmations and is not supposed to be initiated.
 * Contains content builders and content translators.
 */
public class USSDApi {

    private USSDApi() {
    }

    /**
     * Returns a builder for USSD Send Request.
     * For more information, refer: {@link USSDSendRequestBuilder}
     *
     * @return USSD Send Request builder
     */
    public static USSDSendRequestBuilder newSendUSSD() {
        return new USSDSendRequestBuilder();
    }

    /**
     * Returns USSD Send Confirmation.
     * For more information, refer: {@link USSDSendConfirmation}
     *
     * @param content USSD Send Confirmation content
     * @return confirmation for USSD Send service
     */
    public static USSDSendConfirmation ussdSendConfirmation(String content){
        return AbstractSDKBuilders.fromJson(USSDSendConfirmation.class,content);
    }

    /**
     * Returns USSD Receive Indication
     * For more information, refer: {@link USSDReceiveIndication}
     *
     * @param content USSD receive indication content
     * @return indication for USSD Receive service
     */
    public static USSDReceiveIndication ussdReceiveIndication(String content){
        return AbstractSDKBuilders.fromJson(USSDReceiveIndication.class,content);
    }

    /**
     * Returns status code and status detail for USSD receive response.
     * For more information, refer: {@link USSDReceiveResponseFactory}
     *
     * @param statusCode status code for USSD receive response
     * @param statusDetail status detail for USSD receive response
     * @return status code and status detail for USSD receive response
     */
    public static USSDReceiveResponse newReceiveUSSD(String statusCode, String statusDetail) {
        return new USSDReceiveResponseFactory().create(statusCode,statusDetail);
    }
}