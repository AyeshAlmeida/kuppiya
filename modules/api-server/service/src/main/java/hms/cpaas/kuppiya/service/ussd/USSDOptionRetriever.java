package hms.cpaas.kuppiya.service.ussd;

import hms.cpaas.kuppiya.service.config.ussd.MenuOption;
import reactor.core.publisher.Flux;

public interface USSDOptionRetriever {
    Flux<MenuOption> retrieveMenuOptions(String actionId, String sessionId, String sourceAddress);
}
