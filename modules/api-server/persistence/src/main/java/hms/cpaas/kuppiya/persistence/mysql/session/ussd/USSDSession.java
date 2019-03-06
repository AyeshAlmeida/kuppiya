package hms.cpaas.kuppiya.persistence.mysql.session.ussd;

import hms.cpaas.kuppiya.api.domain.ApiLevelSession;
import hms.cpaas.kuppiya.persistence.mysql.session.SessionStatus;

import java.time.LocalDateTime;

public class USSDSession implements ApiLevelSession {
    private long id;
    private String sessionId;
    private String maskedMsisdn;
    private String ussdOperation;
    private String currentMenu;
    private String currentAction;
    private LocalDateTime lastUpdatedTime;
    private SessionStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMaskedMsisdn() {
        return maskedMsisdn;
    }

    public void setMaskedMsisdn(String maskedMsisdn) {
        this.maskedMsisdn = maskedMsisdn;
    }

    public String getUssdOperation() {
        return ussdOperation;
    }

    public void setUssdOperation(String ussdOperation) {
        this.ussdOperation = ussdOperation;
    }

    public String getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(String currentMenu) {
        this.currentMenu = currentMenu;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    @Override
    public String getSourceAddress() {
        return maskedMsisdn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("USSDSession{");
        sb.append("id=").append(id);
        sb.append(", sessionId='").append(sessionId).append('\'');
        sb.append(", maskedMsisdn='").append(maskedMsisdn).append('\'');
        sb.append(", ussdOperation='").append(ussdOperation).append('\'');
        sb.append(", currentMenu='").append(currentMenu).append('\'');
        sb.append(", currentAction='").append(currentAction).append('\'');
        sb.append(", lastUpdatedTime=").append(lastUpdatedTime);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
