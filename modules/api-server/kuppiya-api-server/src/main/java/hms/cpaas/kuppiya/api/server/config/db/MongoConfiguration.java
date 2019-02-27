package hms.cpaas.kuppiya.api.server.config.db;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import hms.cpaas.kuppiya.persistence.mongo.converter.SessionConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {
    @Value("${db.config.mongo.database.url}")
    private String mongoConnectionString;
    @Value("${db.config.mongo.database.name}")
    private String databaseName;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoConnectionString);
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public CustomConversions customConversions() {
        //todo: add custom converters here
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new SessionConverter());
        return new MongoCustomConversions(converterList);
    }
}
