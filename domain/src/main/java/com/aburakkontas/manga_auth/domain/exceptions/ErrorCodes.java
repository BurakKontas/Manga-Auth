package com.aburakkontas.manga_auth.domain.exceptions;

public class ErrorCodes {
    public static final int USER_ALREADY_EXISTS = 600;
    public static final int FAILED_TO_REGISTER_USER = 601;
    public static final int USER_NOT_FOUND_OR_PASSWORD_INCORRECT = 602;
    public static final int USER_IS_NOT_VERIFIED = 603;
    public static final int FAILED_TO_LOGOUT_USER = 604;
    public static final int FAILED_TO_CHANGE_PASSWORD = 605;
    public static final int FAILED_TO_SEND_PASSWORD_CHANGE_EMAIL = 606;
    public static final int FAILED_TO_RETRIEVE_USER = 607;
    public static final int FAILED_TO_VALIDATE_TOKEN = 608;
    public static final int FAILED_TO_REFRESH_TOKEN = 609;
    public static final int FAILED_TO_RETRIEVE_GOOGLE_IDENTITY_PROVIDER = 610;
    public static final int FAILED_TO_EXCHANGE_OAUTH2_CODE_FOR_JWT = 611;
    public static final int VALIDATION_ERROR = 612;
    public static final int MAPPING_ERROR = 613;
    public static final int FAILED_TO_VERIFY_EMAIL = 614;
    public static final int FAILED_TO_EXTRACT_TOKEN = 615;
    public static final int WEBHOOK_FAILED = 616;
}
