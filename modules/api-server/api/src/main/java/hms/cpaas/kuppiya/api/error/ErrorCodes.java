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

/**
 *
 */
public class ErrorCodes {
    public static final String SUCCESS = "S1000";

    public static final String UNEXPECTED_SERVER_ERROR = "E14010";
    public static final String UNEXPECTED_CLIENT_ERROR = "E14011";

    //System configuration related errors E14100 - E14199
    public static final String CONFIG_IDENTIFIER_ERROR = "E14100";
    public static final String NOT_SUPPORTED_CONFIG_TYPE = "E14101";
    public static final String CONFIG_FILE_NOT_AVAILABLE = "E14102";
    public static final String CONFIG_LOAD_ERROR = "E14103";
    public static final String USSD_FLOW_CONFIG_NOT_FOUND = "E14104";
}
