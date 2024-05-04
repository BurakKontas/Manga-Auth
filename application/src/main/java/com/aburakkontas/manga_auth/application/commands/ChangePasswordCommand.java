package com.aburakkontas.manga_auth.application.commands;

import com.aburakkontas.manga_auth.application.validators.Validatable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ChangePasswordCommand extends Validatable {
    private String email;
    private String currentPassword;
    private String newPassword;
}
