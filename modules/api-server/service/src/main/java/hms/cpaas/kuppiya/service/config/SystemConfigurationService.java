package hms.cpaas.kuppiya.service.config;

import hms.cpaas.kuppiya.service.config.ussd.USSDFlowConfig;

public interface SystemConfigurationService {
    USSDFlowConfig loadUSSDFlowConfigWithError();
}
