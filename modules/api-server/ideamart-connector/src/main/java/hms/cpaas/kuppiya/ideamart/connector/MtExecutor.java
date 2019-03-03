package hms.cpaas.kuppiya.ideamart.connector;

import reactor.core.publisher.Mono;

public interface MtExecutor<T extends Request, E extends Confirmation> {
    Mono<E> sendRequest(T mtRequest);
}
