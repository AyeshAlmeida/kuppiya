package hms.cpaas.kuppiya.handler;

import hms.cpaas.kuppiya.persistence.mongo.subscriber.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriberHandler {
    @Autowired
    private SubscriberService subscriberService;

    public void echo() {
        subscriberService.findAll().subscribe();
    }
}
