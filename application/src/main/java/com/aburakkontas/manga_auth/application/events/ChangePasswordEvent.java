package com.aburakkontas.manga_auth.application.events;

import lombok.Data;

@Data
public class ChangePasswordEvent {
    private String email;
    private String currentPassword;
    private String newPassword;
}
