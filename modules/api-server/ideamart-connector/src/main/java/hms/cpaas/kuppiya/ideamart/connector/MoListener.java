package hms.cpaas.kuppiya.ideamart.connector;

import reactor.core.publisher.Mono;

public interface MoListener<T extends Indication, E extends Response> {
    Mono<E> onReceived(T moRequest);
}
