package hms.cpaas.kuppiya.persistence.mongo.faculty;

import hms.cpaas.kuppiya.persistence.mongo.BaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FacultyService extends BaseService<Faculty> {
    Mono<Faculty> findByFacultyId(String facultyId);
    Mono<Faculty> findByFacultyCode(String facultyCode);
    Flux<Faculty> findByFacultyIdIn(List<String> facultyIds);
    Mono<Faculty> updateByFacultyId(Faculty entity, String facultyId);
    Mono<Faculty> updateByFacultyFacultyCode(Faculty entity, String facultyCode);
}
