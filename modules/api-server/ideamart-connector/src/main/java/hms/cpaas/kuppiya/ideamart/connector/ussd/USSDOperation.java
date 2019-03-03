package hms.cpaas.kuppiya.ideamart.connector.ussd;

public enum USSDOperation {

    _MO_INIT("mo-init"),

    _MO_CONT("mo-cont"),

    _MT_INIT("mt-init"),

    _MT_CONT("mt-cont"),

    _MT_FIN("mt_fin");

    private String value;

    USSDOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
