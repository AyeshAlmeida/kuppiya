package hms.cpaas.kuppiya.ideamart.connector.sms;

public enum ReportDeliveryStaus {

    _DELIVERED("DELIVERED"),

    _EXPIRED("EXPIRED"),

    _DELETED("DELETED"),

    _UNDELIVERABLE("UNDELIVERABLE"),

    _ACCEPTED("ACCEPTED"),

    _UNKNOWN("UNKNOWN"),

    _REJECTED("REJECTED");

    private String value;

    ReportDeliveryStaus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
