package hms.cpaas.kuppiya.persistence.mongo.apiSession;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ApiSessionDataRepositoryImpl {
    private static final String COLLECTION_NAME = "session_data";
    private final ReactiveMongoTemplate template;

    public ApiSessionDataRepositoryImpl(ReactiveMongoTemplate template) {
        this.template = template;
    }

    public Mono<Map> findSessionData(final String sessionId, final String sourceAddress) {
        Query sessionDataQuery = Query.query(Criteria.where("session-id").is(sessionId).and("source-address").is(sourceAddress));
        return template.findOne(sessionDataQuery, Map.class, COLLECTION_NAME);
    }

    public Mono<Map> saveSessionData(final String sessionId, final String sourceAddress) {
        Map<String, String> data = new HashMap<>();
        data.put("session-id", sessionId);
        data.put("source-address", sourceAddress);
        return template.save(data, COLLECTION_NAME);
    }

    public Mono<Map> updateSessionAction(final String sessionId, final String sourceAddress, final String action, final String value) {
        Query sessionDataQuery = Query.query(Criteria.where("session-id").is(sessionId).and("source-address").is(sourceAddress));
        Update update = Update.update(action, value);
        return findSessionData(sessionId, sourceAddress)
                .flatMap(data -> template.updateFirst(sessionDataQuery, update, COLLECTION_NAME))
                .flatMap(updateResult -> findSessionData(sessionId, sourceAddress));
    }
}