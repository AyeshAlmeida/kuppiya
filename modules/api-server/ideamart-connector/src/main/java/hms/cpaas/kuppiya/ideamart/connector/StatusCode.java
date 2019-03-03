package hms.cpaas.kuppiya.ideamart.connector;

public enum StatusCode {
    S1000("S1000"),
    E1313("E1313"),
    E1303("E1303"),
    E1312("E1312"),
    E1309("E1309"),
    E1311("E1311"),
    E1315("E1315"),
    E1317("E1317"),
    E1341("E1341"),
    E1334("E1334"),
    E1335("E1335"),
    E1601("E1601"),
    E1342("E1342"),
    E1343("E1343"),
    E1325("E1325"),
    E1331("E1331"),
    E1308("E1308"),
    E1318("E1318"),
    E1319("E1319"),
    E1603("E1603");

    private String value;

    StatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
