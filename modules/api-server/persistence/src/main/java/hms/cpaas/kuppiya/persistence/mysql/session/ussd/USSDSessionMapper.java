package hms.cpaas.kuppiya.persistence.mysql.session.ussd;

import hms.cpaas.kuppiya.persistence.mysql.session.SessionStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class USSDSessionMapper implements RowMapper<USSDSession> {
    @Override
    public USSDSession mapRow(ResultSet resultSet, int i) throws SQLException {
        USSDSession ussdSession = new USSDSession();
        ussdSession.setId(resultSet.getLong("id"));
        ussdSession.setSessionId(resultSet.getString("session_id"));
        ussdSession.setMaskedMsisdn(resultSet.getString("masked_msisdn"));
        ussdSession.setUssdOperation(resultSet.getString("ussd_operation"));
        ussdSession.setCurrentMenu(resultSet.getString("current_menu"));
        ussdSession.setCurrentAction(resultSet.getString("current_action"));
        Timestamp lastUpdatedTime = resultSet.getTimestamp("last_updated_time");
        ussdSession.setLastUpdatedTime(lastUpdatedTime != null? lastUpdatedTime.toLocalDateTime(): null);
        String status = resultSet.getString("status");
        ussdSession.setStatus(status != null? SessionStatus.valueOf(status): SessionStatus.UNKNOWN);
        return ussdSession;
    }
}
