package hms.cpaas.kuppiya.ideamart.connector;

/**
 * Message encoding type.
 * <p>
 * Following encoding types are allowed in TAP platform.
 * </p>
 * <ol>
 *     <li>0 - Text</li>
 *     <li>240 - Flash SMS</li>
 *     <li>245 - Binary SMS</li>
 * </ol>
 */
public enum MessageEncoding {

    /**
     * Message is encoded as a text message.
     */
    _0("0"),

    /**
     * Message is encoded as a text message, views as a flash message.
     */
    _240("240"),

    /**
     * Message is encoded as a binary message.
     */
    _245("245"),

    _440("440");
    private String value;

    MessageEncoding(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
