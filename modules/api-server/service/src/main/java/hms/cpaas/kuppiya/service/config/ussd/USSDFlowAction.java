package hms.cpaas.kuppiya.service.config.ussd;

import java.util.List;

public class USSDFlowAction {
    private String id;
    private String title;
    private int priority;
    private List<MenuOption> options;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<MenuOption> getOptions() {
        return options;
    }

    public void setOptions(List<MenuOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("USSDFlowAction{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", priority=").append(priority);
        sb.append(", options=").append(options);
        sb.append('}');
        return sb.toString();
    }
}
