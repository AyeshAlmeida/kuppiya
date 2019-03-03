package hms.cpaas.kuppiya.handler;

import hms.cpaas.kuppiya.persistence.mongo.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriberHandler {
    @Autowired
    private AppUserService appUserService;

    public void echo() {
        appUserService.findAll().subscribe();
    }
}
