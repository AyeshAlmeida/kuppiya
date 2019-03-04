package hms.cpaas.kuppiya.service.impl;

import hms.cpaas.kuppiya.api.domain.FacultyObject;
import hms.cpaas.kuppiya.api.domain.LocationObject;
import hms.cpaas.kuppiya.api.domain.SubjectObject;
import hms.cpaas.kuppiya.api.domain.UniversityObject;
import hms.cpaas.kuppiya.api.messages.ApiResponse;
import hms.cpaas.kuppiya.persistence.mongo.faculty.Faculty;
import hms.cpaas.kuppiya.persistence.mongo.faculty.FacultyService;
import hms.cpaas.kuppiya.persistence.mongo.id.UniqueIdService;
import hms.cpaas.kuppiya.persistence.mongo.location.Location;
import hms.cpaas.kuppiya.persistence.mongo.location.LocationService;
import hms.cpaas.kuppiya.persistence.mongo.subject.Subject;
import hms.cpaas.kuppiya.persistence.mongo.subject.SubjectService;
import hms.cpaas.kuppiya.persistence.mongo.university.University;
import hms.cpaas.kuppiya.persistence.mongo.university.UniversityService;
import hms.cpaas.kuppiya.service.InternalRequestHandler;
import hms.cpaas.kuppiya.service.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class InternalRequestHandlerImpl implements InternalRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(InternalRequestHandlerImpl.class);

    @Autowired
    private LocationService locationService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private UniqueIdService uniqueIdService;

    @Override
    public Mono<ApiResponse> createLocation(String requestId, LocationObject location) {
        MDC.put("trxId", requestId);
        try {
            Location entity = new Location();
            entity.setUpdatedBy("ADMIN");
            entity.setCreatedBy("ADMIN");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setUpdatedDate(LocalDateTime.now());
            entity.setLocationName(location.getLocationName());
            return uniqueIdService
                    .updateLocationId()
                    .map(uniqueId -> {
                        long locationId = uniqueId.getLocationId();
                        entity.setLocationId(AppUtils.generateId("LOC", locationId));
                        return entity;
                    })
                    .flatMap(locationService::create)
                    .doOnSuccess(loc -> LOGGER.info("Created Location[{}] Successfully", loc))
                    .map(e -> new ApiResponse(requestId, "S1000", "SUCCESS"));
        } finally {
            MDC.clear();
        }
    }

    @Override
    public Mono<ApiResponse> createSubject(String requestId, SubjectObject subject) {
        MDC.put("trxId", requestId);
        try {
            Subject entity = new Subject();
            entity.setUpdatedBy("ADMIN");
            entity.setCreatedBy("ADMIN");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setUpdatedDate(LocalDateTime.now());
            entity.setSubjectCode(subject.getSubjectCode());
            entity.setSubjectName(subject.getSubjectName());
            return uniqueIdService
                    .updateSubjectId()
                    .map(uniqueId -> {
                        long subjectId = uniqueId.getSubjectId();
                        entity.setSubjectId(AppUtils.generateId("SBJ", subjectId));
                        return entity;
                    })
                    .flatMap(subjectService::create)
                    .doOnSuccess(loc -> LOGGER.info("Created Subject[{}] Successfully", loc))
                    .map(e -> new ApiResponse(requestId, "S1000", "SUCCESS"));
        } finally {
            MDC.clear();
        }
    }

    @Override
    public Mono<ApiResponse> createFaculty(String requestId, FacultyObject faculty) {
        MDC.put("trxId", requestId);
        try {
            Faculty entity = new Faculty();
            entity.setUpdatedBy("ADMIN");
            entity.setCreatedBy("ADMIN");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setUpdatedDate(LocalDateTime.now());
            entity.setFacultyCode(faculty.getFacultyCode());
            entity.setFacultyName(faculty.getFacultyName());
            entity.setFacultyDescription(faculty.getFacultyDescription());
            return uniqueIdService
                    .updateFacultyId()
                    .map(uniqueId -> {
                        long facultyId = uniqueId.getFacultyId();
                        entity.setFacultyId(AppUtils.generateId("FAC", facultyId));
                        return entity;
                    })
                    .flatMap(e -> subjectService.findBySubjectIds(faculty.getSubjects()).collectList())
                    .map(subjects -> {
                        entity.setSubjects(subjects);
                        return entity;
                    })
                    .flatMap(facultyService::create)
                    .doOnSuccess(loc -> LOGGER.info("Created Faculty[{}] Successfully", loc))
                    .map(e -> new ApiResponse(requestId, "S1000", "SUCCESS"));
        } finally {
            MDC.clear();
        }
    }

    @Override
    public Mono<ApiResponse> createUniversity(String requestId, UniversityObject university) {
        MDC.put("trxId", requestId);
        try {
            University entity = new University();
            entity.setUpdatedBy("ADMIN");
            entity.setCreatedBy("ADMIN");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setUpdatedDate(LocalDateTime.now());
            entity.setName(university.getName());
            entity.setUniversityCode(university.getUniversityCode());
            return uniqueIdService
                    .updateUniversityId()
                    .map(uniqueId -> {
                        long universityId = uniqueId.getUniversityId();
                        entity.setUniversityId(AppUtils.generateId("UNI", universityId));
                        return entity;
                    })
                    .flatMap(e -> facultyService.findByFacultyIdIn(university.getFaculties()).collectList())
                    .map(faculties -> {
                        entity.setFaculties(faculties);
                        return entity;
                    })
                    .flatMap(e -> locationService.findByLocationIdsIn(university.getLocations()).collectList())
                    .map(locations -> {
                        entity.setLocations(locations);
                        return entity;
                    })
                    .flatMap(universityService::create)
                    .doOnSuccess(loc -> LOGGER.info("Created University[{}] Successfully", loc))
                    .map(e -> new ApiResponse(requestId, "S1000", "SUCCESS"));
        } finally {
            MDC.clear();
        }
    }
}
