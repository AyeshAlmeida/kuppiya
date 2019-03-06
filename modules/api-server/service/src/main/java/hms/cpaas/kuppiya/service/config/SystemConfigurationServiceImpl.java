package hms.cpaas.kuppiya.service.config;

import hms.cpaas.kuppiya.api.error.ErrorCodes;
import hms.cpaas.kuppiya.api.error.KuppiyaApiServerException;
import hms.cpaas.kuppiya.service.config.ussd.USSDFlowConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemConfigurationServiceImpl implements SystemConfigurationService {
    @Autowired
    private ConfigurationLoaderManager configurationLoaderManager;

    @Override
    public USSDFlowConfig loadUSSDFlowConfigWithError() {
        Optional<USSDFlowConfig> opt = configurationLoaderManager
                .loadConfiguration("USSD_FLOW");
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw KuppiyaApiServerException.serverError(ErrorCodes.USSD_FLOW_CONFIG_NOT_FOUND,
                    "USSD Flow configuration is not found");
        }
    }
}
