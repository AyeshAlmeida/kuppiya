package hms.cpaas.kuppiya;

import hms.cpaas.kuppiya.persistence.mongo.id.UniqueId;
import hms.cpaas.kuppiya.persistence.mongo.id.UniqueIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableWebFlux
public class KuppiyaApiServerApplication implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(KuppiyaApiServerApplication.class);

    @Autowired
    private UniqueIdService uniqueIdService;

    public static void main(String... args) {
        LOGGER.info("============================================");
        LOGGER.info("======== Starting KUPPIYA-API-SERVER ========");
        LOGGER.info("============================================");
        SpringApplication.run(KuppiyaApiServerApplication   .class, args);
        LOGGER.info("============================================");
        LOGGER.info("================ Started ===================");
        LOGGER.info("============================================");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        uniqueIdService
                .findUniqueIdByName()
                .switchIfEmpty(Mono.defer(() -> uniqueIdService.create(initialUniqueId())))
                .doOnSuccess(uniqueId -> LOGGER.info("Found UniqueId Collection [{}]", uniqueId))
                .subscribe();
    }

    private UniqueId initialUniqueId() {
        UniqueId uniqueId = new UniqueId();
        uniqueId.setName("unique_id_collection");
        uniqueId.setNotificationId(0);
        uniqueId.setSessionId(0);
        uniqueId.setSubjectId(0);
        uniqueId.setUniversityId(0);
        uniqueId.setNotificationId(0);
        uniqueId.setCreatedBy("ADMIN");
        uniqueId.setUpdatedBy("ADMIN");
        uniqueId.setCreatedDate(LocalDateTime.now());
        uniqueId.setUpdatedDate(LocalDateTime.now());
        return uniqueId;
    }
}
