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

package hms.cpaas.kuppiya.api.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Component
@Order(-2)
public class CustomErrorTranslator<T extends Throwable> extends DefaultErrorAttributes {
    private static final Logger LOGGER = LoggerFactory.getLogger("CustomErrorTranslator");


    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        return addErrorDetails(request);
    }

    protected Map<String, Object> addErrorDetails(ServerRequest request) {
        Map<String, Object> errorAttributes = new HashMap<>();

        Throwable ex = getError(request);

        if (ex instanceof KuppiyaApiServerException) {
            KuppiyaApiServerException rex = (KuppiyaApiServerException) ex;
            errorAttributes.put("errorMessage", rex.getMessage());
            errorAttributes.put("status", rex.getErrorType().getHttpErrorCode().value());
            errorAttributes.put("errorCode", rex.getErrorCode());
            Map<String, String> additionalParams = rex.getAdditionalParams();
            if(additionalParams != null) {
                errorAttributes.putAll(additionalParams);
            }
        } else if (ex instanceof ResponseStatusException) {
            errorAttributes.put("message", ex.getMessage());
            errorAttributes.put("status", ((ResponseStatusException) ex).getStatus().value());
            LOGGER.error("Error", ex);
        } else {
            errorAttributes.put("errorMessage", "Unexpected server error");
            errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            LOGGER.error("Unexpected error has happened", ex);
        }

        return errorAttributes;
    }

}