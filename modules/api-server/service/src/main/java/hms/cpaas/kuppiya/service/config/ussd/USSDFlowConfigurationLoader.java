package hms.cpaas.kuppiya.service.config.ussd;

import com.google.gson.reflect.TypeToken;
import hms.cpaas.kuppiya.service.config.AbstractConfigurationLoader;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component("USSDFlowConfigurationLoader")
public class USSDFlowConfigurationLoader extends AbstractConfigurationLoader<USSDFlowConfig> {
    private final Type configTypeDef = new TypeToken<USSDFlowConfig>() {
    }.getType();

    @Override
    protected Type getConfigTypeDef() {
        return configTypeDef;
    }

    @Override
    protected String getConfigFileName() {
        return "ussd-menu-config.json";
    }

    @Override
    protected USSDFlowConfig createConfigurationDataObject(Object obj) {
        return (USSDFlowConfig) obj;
    }

    @Override
    public String getConfigType() {
        return "USSD_FLOW";
    }
}
