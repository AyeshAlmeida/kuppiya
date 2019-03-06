package hms.cpaas.kuppiya.service.config.ussd;

public class MenuOption {
    private String id;
    private String value;
    private String ref;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MenuOption{");
        sb.append("id='").append(id).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", ref='").append(ref).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
