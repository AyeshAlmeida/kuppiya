package hms.cpaas.kuppiya.handler;

import hms.cpaas.kuppiya.api.domain.FacultyObject;
import hms.cpaas.kuppiya.api.domain.LocationObject;
import hms.cpaas.kuppiya.api.domain.SubjectObject;
import hms.cpaas.kuppiya.api.domain.UniversityObject;
import hms.cpaas.kuppiya.service.InternalRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class InternalRouteHandler {
    @Autowired
    private InternalRequestHandler internalRequestHandler;

    public Mono<ServerResponse> createLocation(final String requestId, final ServerRequest request) {
        return request
                .bodyToMono(LocationObject.class)
                .flatMap(location -> internalRequestHandler.createLocation(requestId, location))
                .flatMap(response -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(response)));
    }

    public Mono<ServerResponse> createSubject(final String requestId, final ServerRequest request) {
        return request
                .bodyToMono(SubjectObject.class)
                .flatMap(subject -> internalRequestHandler.createSubject(requestId, subject))
                .flatMap(response -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(response)));
    }

    public Mono<ServerResponse> createFaculty(final String requestId, final ServerRequest request) {
        return request
                .bodyToMono(FacultyObject.class)
                .flatMap(faculty -> internalRequestHandler.createFaculty(requestId, faculty))
                .flatMap(response -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(response)));
    }

    public Mono<ServerResponse> createUniversity(final String requestId, final ServerRequest request) {
        return request
                .bodyToMono(UniversityObject.class)
                .flatMap(university -> internalRequestHandler.createUniversity(requestId, university))
                .flatMap(response -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(response)));
    }
}
