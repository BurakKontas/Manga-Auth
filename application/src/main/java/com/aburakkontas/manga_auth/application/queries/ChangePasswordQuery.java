package com.aburakkontas.manga_auth.application.queries;

import com.aburakkontas.manga_auth.application.validators.Validatable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ChangePasswordQuery extends Validatable {
    private String email;
    private String currentPassword;
    private String newPassword;
}
