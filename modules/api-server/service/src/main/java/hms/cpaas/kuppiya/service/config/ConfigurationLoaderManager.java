package hms.cpaas.kuppiya.service.config;

import hms.cpaas.kuppiya.api.error.ErrorCodes;
import hms.cpaas.kuppiya.api.error.KuppiyaApiServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ConfigurationLoaderManager implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationLoaderManager.class);

    private ApplicationContext applicationContext;
    private Map<String, ConfigurationLoader> configurationLoaders = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @PostConstruct
    public void init() {
        Map<String, ConfigurationLoader> loaderBeansMap = applicationContext.getBeansOfType(ConfigurationLoader.class);
        if (loaderBeansMap != null) {
            for (ConfigurationLoader cl : loaderBeansMap.values()) {
                configurationLoaders.put(cl.getConfigType(), cl);
            }
        } else {
            throw new IllegalStateException("No configuration loader is configured");
        }
    }

    public <T extends ConfigurationData> Optional<T> loadConfiguration(String configType) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Loading configuration for [{}]", configType);
        }

        ConfigurationLoader configurationLoader = configurationLoaders.get(configType);
        if (configurationLoader != null) {
            return configurationLoader.loadData();
        } else {
            throw KuppiyaApiServerException.serverError(ErrorCodes.NOT_SUPPORTED_CONFIG_TYPE,
                    "Config loader not found for [%s]", configType);
        }

    }
}
