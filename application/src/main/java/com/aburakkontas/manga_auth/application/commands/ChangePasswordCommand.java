package com.aburakkontas.manga_auth.application.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordCommand {
    private String email;
    private String currentPassword;
    private String newPassword;
}
