package com.aburakkontas.manga_auth.application.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordCommand {
    private String email;
    private String currentPassword;
    private String newPassword;
}
