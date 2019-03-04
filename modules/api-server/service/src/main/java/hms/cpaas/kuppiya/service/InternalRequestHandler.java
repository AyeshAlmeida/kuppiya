package hms.cpaas.kuppiya.service;

import hms.cpaas.kuppiya.api.domain.FacultyObject;
import hms.cpaas.kuppiya.api.domain.LocationObject;
import hms.cpaas.kuppiya.api.domain.SubjectObject;
import hms.cpaas.kuppiya.api.domain.UniversityObject;
import hms.cpaas.kuppiya.api.messages.ApiResponse;
import reactor.core.publisher.Mono;

public interface InternalRequestHandler {
    Mono<ApiResponse> createLocation(final String requestId, final LocationObject location);
    Mono<ApiResponse> createSubject(final String requestId, final SubjectObject subject);
    Mono<ApiResponse> createFaculty(final String requestId, final FacultyObject faculty);
    Mono<ApiResponse> createUniversity(final String requestId, final UniversityObject university);
}
