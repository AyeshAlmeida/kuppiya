package hms.cpaas.kuppiya.service.ussd;

import hms.cpaas.kuppiya.persistence.mongo.apiSession.ApiSessionDataRepositoryImpl;
import hms.cpaas.kuppiya.persistence.mongo.appUser.AppUser;
import hms.cpaas.kuppiya.persistence.mongo.appUser.AppUserService;
import hms.cpaas.kuppiya.persistence.mongo.faculty.Faculty;
import hms.cpaas.kuppiya.persistence.mongo.university.University;
import hms.cpaas.kuppiya.persistence.mongo.university.UniversityService;
import hms.cpaas.kuppiya.service.config.ussd.MenuOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class USSDOptionRetrieverImpl implements USSDOptionRetriever {
    private final UniversityService universityService;
    private final AppUserService appUserService;
    private final ApiSessionDataRepositoryImpl apiSessionDataRepository;

    @Autowired
    public USSDOptionRetrieverImpl(UniversityService universityService,
                                   AppUserService appUserService,
                                   ApiSessionDataRepositoryImpl apiSessionDataRepository) {
        this.universityService = universityService;
        this.appUserService = appUserService;
        this.apiSessionDataRepository = apiSessionDataRepository;
    }

    @Override
    public Flux<MenuOption> retrieveMenuOptions(String actionId, String sessionId, String sourceAddress) {
        if (actionId.equalsIgnoreCase("selectUniversity")) {
            return universityService
                    .findAll()
                    .map(e -> {
                        MenuOption option = new MenuOption();
                        option.setId(e.getUniversityId());
                        option.setValue(e.getUniversityCode());
                        return option;
                    });
        } else if (actionId.equalsIgnoreCase("selectFaculty")) {
            return apiSessionDataRepository
                    .findSessionData(sessionId, sourceAddress)
                    .map(e -> e.get("selectUniversity"))
                    .flatMap(uniCode -> universityService.findByUniversityCode((String) uniCode))
                    .flatMapIterable(University::getFaculties)
                    .map(e -> {
                        MenuOption option = new MenuOption();
                        option.setId(e.getFacultyId());
                        option.setValue(e.getFacultyCode());
                        return option;
                    });
        } else if (actionId.equalsIgnoreCase("selectSubject")) {
            return appUserService
                    .findByMaskedMsisdn(sourceAddress)
                    .map(AppUser::getFaculty)
                    .flatMapIterable(Faculty::getSubjects)
                    .map(e -> {
                        MenuOption option = new MenuOption();
                        option.setId(e.getSubjectId());
                        option.setValue(e.getSubjectCode());
                        return option;
                    });
        } else if (actionId.equalsIgnoreCase("selectLocation")) {
            return appUserService
                    .findByMaskedMsisdn(sourceAddress)
                    .map(AppUser::getUniversity)
                    .flatMapIterable(University::getLocations)
                    .map(e -> {
                        MenuOption option = new MenuOption();
                        option.setId(e.getLocationId());
                        option.setValue(e.getLocationName());
                        return option;
                    });
        } else {
            return Flux.empty();
        }
    }
}
