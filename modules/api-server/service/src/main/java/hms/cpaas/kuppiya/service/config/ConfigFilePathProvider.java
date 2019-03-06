/*
 * (C) Copyright 2010- 2019 hSenid Mobile Solutions (Pvt) Limited.
 * All Rights Reserved.
 *
 * These materials are unpublished, proprietary, confidential source code of
 * hSenid Mobile Solutions (Pvt) Limited and constitute a TRADE SECRET
 * of hSenid Mobile Solutions (Pvt) Limited.
 *
 * hSenid Mobile Solutions (Pvt) Limited retains all title to and intellectual
 * property rights in these materials.
 */

package hms.cpaas.kuppiya.service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Optional;

public class ConfigFilePathProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFilePathProvider.class);

    public static Optional<String> getApiConfigBasePath() {
        return loadFilePath("api-config");
    }

    public static Optional<String> loadFilePath(String pathFragment) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Loading config file location for [{}]", pathFragment);
        }
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(pathFragment);
            if (resource != null && resource.exists()) {
                return Optional.ofNullable(resource.getURI().getPath());
            } else {
                return Optional.empty();
            }
        } catch (Exception ex) {
            LOGGER.error(String.format("Error while loading config location for [%s]", pathFragment), ex);
            return Optional.empty();
        }
    }


}
