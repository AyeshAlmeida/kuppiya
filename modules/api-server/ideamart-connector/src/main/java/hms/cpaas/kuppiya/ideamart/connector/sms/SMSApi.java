package hms.cpaas.kuppiya.ideamart.connector.sms;

import hms.cpaas.kuppiya.ideamart.connector.AbstractSDKBuilders;
import hms.cpaas.kuppiya.ideamart.connector.sms.domain.*;

/**
 * <p>This is the main utility class for generating TAP SMS api related requests/responses/indications/confirmations and is not supposed to be initiated.
 * Contains message builders and message translators.</p>
 */
public class SMSApi {

    private SMSApi() {
    }

    /**
     * Returns a builder for MT SMS message.
     * For more information, refer: {@link SMSMTRequestBuilder}
     *
     * @return Mobile terminated SMS message builder
     */
    public static SMSMTRequestBuilder newMtSMS() {
        return new SMSMTRequestBuilder();
    }

    /**
     * Returns Mobile Terminated confirmation.
     * For more information, refer: {@link SMSMTConfirmation}
     *
     * @param message Mobile Terminated confirmation message
     * @return confirmation for Mobile Termination
     */
    public static SMSMTConfirmation toMtConfirmation(String message) {
        return AbstractSDKBuilders.fromJson(SMSMTConfirmation.class, message);
    }

    /**
     * Returns Mobile Originated Indication
     * For more information, refer: {@link SMSMOIndication}
     *
     * @param message Mobile Originated indication message
     * @return indication for Mobile Origination
     */
    public static SMSMOIndication toSMSMOIndication(String message) {
        return AbstractSDKBuilders.fromJson(SMSMOIndication.class, message);
    }

    /**
     * Returns status code and status detail for Mobile Originated Response.
     * For more information, refer: {@link SMSMOResponseFactory}
     *
     * @param statusCode   status code for Mobile Originated Response
     * @param statusDetail status detail for Mobile Originated Response
     * @return status code and status detail for Mobile Originated Response
     */
    public static SMSMOResponse newSmsMOResponse(String statusCode, String statusDetail) {
        return new SMSMOResponseFactory().create(statusCode, statusDetail);

    }

    /**
     * Returns SMS Report Receipt Indication
     * For more information, refer: {@link SMSReportReceiptIndication}
     *
     * @param message SMS report receipt indication message
     * @return indication for sms delivery report service
     */
    public static SMSReportReceiptIndication toSMSReportReceiptIndication(String message) {
        return AbstractSDKBuilders.fromJson(SMSReportReceiptIndication.class, message);
    }

    /**
     * Returns status code and status detail for SMS delivery report Response.
     * For more information, refer: {@link SMSReportReceiptResponseFactory}
     *
     * @param statusCode   status code for SMS delivery report status Response
     * @param statusDetail status detail for SMS delivery report statusResponse
     * @return status code and status detail for SMS delivery report status Response
     */
    public static SMSReportReceiptresponse newSMSReportReceiptresponse(String statusCode, String statusDetail) {
        return new SMSReportReceiptResponseFactory().create(statusCode, statusDetail);

    }

}
