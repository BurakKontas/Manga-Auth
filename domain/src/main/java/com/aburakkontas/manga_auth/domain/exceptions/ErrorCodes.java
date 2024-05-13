package com.aburakkontas.manga_auth.domain.exceptions;

public class ErrorCodes {
    public static final String USER_ALREADY_EXISTS = "500A";
    public static final String FAILED_TO_REGISTER_USER = "500B";
    public static final String USER_NOT_FOUND_OR_PASSWORD_INCORRECT = "500C";
    public static final String USER_IS_NOT_VERIFIED = "500D";
    public static final String FAILED_TO_LOGOUT_USER = "500E";
    public static final String FAILED_TO_CHANGE_PASSWORD = "500F";
    public static final String FAILED_TO_SEND_PASSWORD_CHANGE_EMAIL = "500G";
    public static final String FAILED_TO_RETRIEVE_USER = "500H";
    public static final String FAILED_TO_VALIDATE_TOKEN = "500T";
    public static final String FAILED_TO_REFRESH_TOKEN = "500J";
    public static final String FAILED_TO_RETRIEVE_GOOGLE_IDENTITY_PROVIDER = "100A";
    public static final String FAILED_TO_EXCHANGE_OAUTH2_CODE_FOR_JWT = "500T";
    public static final String VALIDATION_ERROR = "500V";
    public static final String MAPPING_ERROR = "500M";
    public static final String FAILED_TO_VERIFY_EMAIL = "500K";
}
