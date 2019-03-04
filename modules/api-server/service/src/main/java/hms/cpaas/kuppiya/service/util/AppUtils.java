package hms.cpaas.kuppiya.service.util;

import java.text.DecimalFormat;

public final class AppUtils {
    public static String generateId(String idPrefix, long idCount) {
        return idPrefix + new DecimalFormat("00000000").format(idCount);
    }
}
