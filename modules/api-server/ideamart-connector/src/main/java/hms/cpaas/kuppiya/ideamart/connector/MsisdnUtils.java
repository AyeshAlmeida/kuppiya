package hms.cpaas.kuppiya.ideamart.connector;

public class MsisdnUtils {

    public static String TEL_ALL = "tel:all";

    public static String toValidMsisdn(String msisdn) {
        return msisdn.startsWith("tel:") ? msisdn : "tel:" + msisdn;
    }

}
