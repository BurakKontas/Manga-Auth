package com.aburakkontas.manga_auth.application.events;

import lombok.Data;

@Data
public class ForgotPasswordEvent {
    private String changePasswordId;
    private String newPassword;
}
