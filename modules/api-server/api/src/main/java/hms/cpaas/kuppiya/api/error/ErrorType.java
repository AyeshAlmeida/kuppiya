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

import org.springframework.http.HttpStatus;


public enum ErrorType {
    CLIENT_ERROR(HttpStatus.BAD_REQUEST),
    AUTH_ERROR(HttpStatus.UNAUTHORIZED),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    DUPLICATE(HttpStatus.CONFLICT),
    PRECONDITION_FAILED(HttpStatus.PRECONDITION_FAILED),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus httpErrorCode;

    ErrorType(HttpStatus httpCode){
        this.httpErrorCode = httpCode;
    }

    public HttpStatus getHttpErrorCode() {
        return httpErrorCode;
    }
}
