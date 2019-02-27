package hms.cpaas.kuppiya.persistence.mongo.converter;

import hms.cpaas.kuppiya.api.domain.Session;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class SessionConverter implements Converter<Document, Session> {
    @Override
    public Session convert(Document document) {
        return null;
    }
}
