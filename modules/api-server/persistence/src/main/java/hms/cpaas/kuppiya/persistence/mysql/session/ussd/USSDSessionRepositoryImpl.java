package hms.cpaas.kuppiya.persistence.mysql.session.ussd;

import hms.cpaas.kuppiya.api.error.ErrorCodes;
import hms.cpaas.kuppiya.api.error.KuppiyaApiServerException;
import hms.cpaas.kuppiya.api.error.MultipleResultsFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class USSDSessionRepositoryImpl implements USSDSessionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(USSDSessionRepositoryImpl.class);

    private static final String FIND_BY_SESSION_ID_AND_MSISDN = "SELECT * FROM ussd_session where session_id = ? and masked_msisdn = ?";
    private static final String UPDATE_SESSION = "UPDATE ussd_session SET ussd_operation = ?, current_menu = ?, current_action = ?, last_updated_time = ?, session_status = ? where session_id = ? and masked_msisdn = ?";

    private final JdbcTemplate template;
    private final RowMapper<USSDSession> mapper;

    @Autowired
    public USSDSessionRepositoryImpl(JdbcTemplate template) {
        this.template = template;
        this.mapper = new USSDSessionMapper();
    }

    @Override
    @Transactional
    public Mono<USSDSession> createUSSDSession(USSDSession session) {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("session_id", session.getSessionId());
            parameters.put("masked_msisdn", session.getMaskedMsisdn());
            parameters.put("ussd_operation", session.getUssdOperation());
            parameters.put("current_menu", session.getCurrentMenu());
            parameters.put("current_action", session.getCurrentAction());
            parameters.put("last_updated_time", Timestamp.valueOf(LocalDateTime.now()));
            parameters.put("status", session.getStatus().toString());
            String[] columnsList = parameters.keySet().toArray(new String[]{});
            SimpleJdbcInsert insert = new SimpleJdbcInsert(template)
                    .withTableName("ussd_session")
                    .usingColumns(columnsList);
            insert.execute(parameters);
            return findUSSDSessionBySessionIdAndMSISDN(session.getSessionId(), session.getMaskedMsisdn());
        } catch (Exception e) {
            throw KuppiyaApiServerException.serverError(ErrorCodes.UNEXPECTED_SERVER_ERROR,
                    "Error occurred while saving ussd-session for [%s/%S]", session.getSessionId(), session.getMaskedMsisdn());
        }
    }

    @Override
    public Mono<USSDSession> findUSSDSessionBySessionIdAndMSISDN(String sessionId, String maskedMsisdn) {
        try {
            LOGGER.debug("Finding USSDSession for SessionId[{}] and MaskedMsisdn[{}]", sessionId, maskedMsisdn);
            List<USSDSession> results = template
                    .query(FIND_BY_SESSION_ID_AND_MSISDN, new Object[]{sessionId, maskedMsisdn}, mapper);
            return getSingleResult(results, sessionId, maskedMsisdn);
        } catch (DataAccessException | MultipleResultsFoundException e) {
            LOGGER.debug("Error occurred while retrieving USSDSession for [{}/{}] ", sessionId, maskedMsisdn, e);
            return Mono.error(e);
        }
    }

    @Override
    @Transactional
    public Mono<USSDSession> updateUSSDSession(USSDSession session) {
        LOGGER.debug("Updating USSDSession[{}]", session);
        return update(session);
    }

    private Mono<USSDSession> update(USSDSession session) {
        int update = template.update(UPDATE_SESSION,
                new Object[]{
                        session.getUssdOperation(),
                        session.getCurrentMenu(),
                        session.getCurrentAction(),
                        Timestamp.valueOf(LocalDateTime.now()),
                        session.getStatus().toString()});
        if (update < 1) {
            return Mono.empty();
        } else if (update == 1) {
            return findUSSDSessionBySessionIdAndMSISDN(session.getSessionId(), session.getMaskedMsisdn());
        } else {
            return Mono.error(new MultipleResultsFoundException(String.format("Multiple Results found for [%s/%s]",
                    session.getSessionId(), session.getMaskedMsisdn())));
        }
    }

    private Mono<USSDSession> getSingleResult(List<USSDSession> results, String sessionId, String maskedMsisdn)
            throws MultipleResultsFoundException {
        if (results != null) {
            if (results.isEmpty()) {
                return Mono.empty();
            } else if (results.size() == 1) {
                return Mono.fromCallable(() -> results.get(0));
            } else {
                throw new MultipleResultsFoundException(
                        String.format("Multiple Results found for [%s/%s]", sessionId, maskedMsisdn));
            }
        } else {
            return Mono.empty();
        }
    }
}
