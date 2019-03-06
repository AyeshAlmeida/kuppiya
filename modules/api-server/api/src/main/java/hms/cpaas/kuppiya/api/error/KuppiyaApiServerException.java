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


import java.util.HashMap;
import java.util.Map;

public class KuppiyaApiServerException extends RuntimeException {
    private String errorCode;
    private ErrorType errorType;
    private String externalErrorCode;
    private Map<String, String> additionalParams = new HashMap<>();

    public KuppiyaApiServerException(ErrorType errorType, String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public KuppiyaApiServerException(ErrorType errorType, String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public Map<String, String> getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(Map<String, String> additionalParams) {
        this.additionalParams = additionalParams;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getExternalErrorCode() {
        return externalErrorCode;
    }

    public void setExternalErrorCode(String externalErrorCode) {
        this.externalErrorCode = externalErrorCode;
    }

    /**
     * Error due to some item not found. Will cause 404 http error
     */
    public static KuppiyaApiServerException notFoundError(String errorCode, String message) {
        return new KuppiyaApiServerException(ErrorType.NOT_FOUND, errorCode, message);
    }

    /**
     * Error due to some item not found. Will cause 404 http error
     */
    public static KuppiyaApiServerException notFoundError(String errorCode, String message, String... parameters) {
        return new KuppiyaApiServerException(ErrorType.NOT_FOUND, errorCode, String.format(message, parameters));
    }

    /**
     * Authentication error. Will cause 401 http error
     */
    public static KuppiyaApiServerException authError(String errorCode, String message) {
        return new KuppiyaApiServerException(ErrorType.AUTH_ERROR, errorCode, message);
    }

    /**
     * 	The parameters were valid but the request failed due to some precondition error. Will cause 412 http error.
     */
    public static KuppiyaApiServerException preconditionFailed(String errorCode, String message, String... parameters) {
        return new KuppiyaApiServerException(ErrorType.PRECONDITION_FAILED, errorCode, String.format(message, parameters));
    }

    /**
     * Some internal server error. Will cause 500 http error.
     */
    public static KuppiyaApiServerException serverError(String errorCode, String message, String... parameters) {
        return new KuppiyaApiServerException(ErrorType.SERVER_ERROR, errorCode, String.format(message, parameters));
    }

    /**
     * Some internal server error. Will cause 500 http error
     */
    public static KuppiyaApiServerException serverError(String errorCode, String message, Throwable cause, String... parameters) {
        return new KuppiyaApiServerException(ErrorType.SERVER_ERROR, errorCode, String.format(message, parameters), cause);
    }

    /**
     * Error due to incorrect client request. Will cause 400 http error
     */
    public static KuppiyaApiServerException clientError(String errorCode, String message) {
        return new KuppiyaApiServerException(ErrorType.CLIENT_ERROR, errorCode, message);
    }

    /**
     * Error due to incorrect client request. Will cause 400 http error
     */
    public static KuppiyaApiServerException clientError(String errorCode, String message, Throwable cause) {
        return new KuppiyaApiServerException(ErrorType.CLIENT_ERROR, errorCode, message, cause);
    }

    /**
     * Error due to incorrect client request. Will cause 400 http error
     */
    public static KuppiyaApiServerException clientError(String errorCode, String message, String... parameters) {
        return new KuppiyaApiServerException(ErrorType.CLIENT_ERROR, errorCode, String.format(message, parameters));
    }

    /**
     * Error due to duplicate items. Will cause 409 http error
     */
    public static KuppiyaApiServerException duplicateItemError(String errorCode, String message, String... parameters) {
        return new KuppiyaApiServerException(ErrorType.DUPLICATE, errorCode, String.format(message, parameters));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KuppiyaApiServerException{");
        sb.append("errorCode='").append(errorCode).append('\'');
        sb.append(", errorType=").append(errorType);
        sb.append(", message=").append(getMessage());
        sb.append(", additionalParams=").append(additionalParams);
        sb.append('}');
        return sb.toString();
    }

    public KuppiyaApiServerException addAdditionalParam(String key, String value) {
        if(this.additionalParams != null){
            this.additionalParams.put(key, value);
        } else {
            this.additionalParams = new HashMap<>();
            this.additionalParams.put(key, value);
        }

        return this;
    }
}
