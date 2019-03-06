package hms.cpaas.kuppiya.service.config;

import com.google.gson.Gson;
import hms.cpaas.kuppiya.api.error.ErrorCodes;
import hms.cpaas.kuppiya.api.error.KuppiyaApiServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Optional;

public abstract class AbstractConfigurationLoader<T extends ConfigurationData> implements ConfigurationLoader<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConfigurationLoader.class);

    private T configuration;

    protected abstract Type getConfigTypeDef();

    protected abstract String getConfigFileName();

    protected abstract T createConfigurationDataObject(Object obj);

    protected Gson gson() {
        return new Gson();
    }

    @PostConstruct
    public void loadConfigFiles() {
        Optional<String> basePath = ConfigFilePathProvider.getApiConfigBasePath();
        if (basePath.isPresent()) {
            this.configuration = loadConfigDataFromFile(getConfigTypeDef(), basePath.get());
        } else {
            throw KuppiyaApiServerException.serverError(ErrorCodes.CONFIG_FILE_NOT_AVAILABLE,
                    "configuration files folder[operator-configs] are not available");
        }

        LOGGER.info("Loaded configurations for [{}]", getConfigType());

    }

    private T loadConfigDataFromFile(Type configTypeDef, String basePath) {
        String fullFilePath = createFullFilePath(basePath);

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fullFilePath));
            Object regConfig = gson().fromJson(inputStreamReader, configTypeDef);
            return createConfigurationDataObject(regConfig);
        } catch (Exception ex) {
            LOGGER.error(String.format("Error loading configuration data from file [%s]", fullFilePath));
            throw KuppiyaApiServerException.serverError(ErrorCodes.CONFIG_LOAD_ERROR,
                    "Error loading configuration data from file [%s]",
                    ex,
                    fullFilePath);
        }
    }

    protected String createFullFilePath(String basePath) {
        String fullFilePath = basePath + "/" + getConfigFileName();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Loading configuration file from [{}]", fullFilePath);
        }
        return fullFilePath;
    }


    /**
     * Load the configuration from file or db
     *
     * @return
     */
    @Override
    public Optional<T> loadData() {
        return Optional.ofNullable(configuration);
    }
}
