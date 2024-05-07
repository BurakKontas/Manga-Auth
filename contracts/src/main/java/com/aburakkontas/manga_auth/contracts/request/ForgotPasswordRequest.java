package com.aburakkontas.manga_auth.contracts.request;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String changePasswordId;
    private String newPassword;
}
