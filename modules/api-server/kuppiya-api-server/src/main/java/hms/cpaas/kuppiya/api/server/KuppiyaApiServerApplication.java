package hms.cpaas.kuppiya.api.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class KuppiyaApiServerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(KuppiyaApiServerApplication.class);

    public static void main(String... args) {
        LOGGER.info("============================================");
        LOGGER.info("======== Starting REG-V3-API-SERVER ========");
        LOGGER.info("============================================");
        SpringApplication.run(KuppiyaApiServerApplication   .class, args);
        LOGGER.info("============================================");
        LOGGER.info("================ Started ===================");
        LOGGER.info("============================================");
    }
}
