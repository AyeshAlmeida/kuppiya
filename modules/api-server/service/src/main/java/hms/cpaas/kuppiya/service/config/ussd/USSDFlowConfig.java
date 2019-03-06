package hms.cpaas.kuppiya.service.config.ussd;

import hms.cpaas.kuppiya.service.config.ConfigurationData;

import java.util.ArrayList;
import java.util.List;

public class USSDFlowConfig implements ConfigurationData {
    private String id;
    private BaseMenu baseMenu;
    private List<USSDFlow> availableFlows;
    private List<MenuOption> commonMenuOptions;
    private USSDFlowAction finishedAction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseMenu getBaseMenu() {
        return baseMenu;
    }

    public void setBaseMenu(BaseMenu baseMenu) {
        this.baseMenu = baseMenu;
    }

    public List<USSDFlow> getAvailableFlows() {
        return availableFlows;
    }

    public void setAvailableFlows(List<USSDFlow> availableFlows) {
        this.availableFlows = availableFlows;
    }

    public List<MenuOption> getCommonMenuOptions() {
        return commonMenuOptions;
    }

    public void setCommonMenuOptions(List<MenuOption> commonMenuOptions) {
        this.commonMenuOptions = commonMenuOptions;
    }

    public USSDFlowAction getFinishedAction() {
        return finishedAction;
    }

    public void setFinishedAction(USSDFlowAction finishedAction) {
        this.finishedAction = finishedAction;
    }

    public USSDFlowConfig getFlowConfig() {
        USSDFlowConfig config = new USSDFlowConfig();
        config.setId(this.getId());
        config.setAvailableFlows(new ArrayList<>(this.getAvailableFlows()));
        config.setBaseMenu(this.getBaseMenu());
        config.setFinishedAction(this.getFinishedAction());
        config.setCommonMenuOptions(new ArrayList<>(this.getCommonMenuOptions()));
        config.setFinishedAction(this.getFinishedAction());
        return config;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("USSDFlowConfig{");
        sb.append("id='").append(id).append('\'');
        sb.append(", baseMenu=").append(baseMenu);
        sb.append(", availableFlows=").append(availableFlows);
        sb.append(", commonMenuOptions=").append(commonMenuOptions);
        sb.append(", finishedAction=").append(finishedAction);
        sb.append('}');
        return sb.toString();
    }
}
