package hms.cpaas.kuppiya.service.config.ussd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class USSDFlow {
    private String id;
    private String name;
    private String title;
    private List<USSDFlowAction> flowActions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<USSDFlowAction> getFlowActions() {
        return flowActions;
    }

    public void setFlowActions(List<USSDFlowAction> flowActions) {
        this.flowActions = flowActions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("USSDFlow{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", flowActions=").append(flowActions);
        sb.append('}');
        return sb.toString();
    }

    public USSDFlow copy() {
        List<USSDFlowAction> actionCopy = new ArrayList<>();
        actionCopy.addAll(flowActions);
        List<USSDFlowAction> collect = actionCopy.stream().map(USSDFlowAction::copy).collect(Collectors.toList());
        USSDFlow ussdFlow = new USSDFlow();
        ussdFlow.setId(id);
        ussdFlow.setName(name);
        ussdFlow.setTitle(title);
        ussdFlow.setFlowActions(collect);
        return ussdFlow;
    }
}
