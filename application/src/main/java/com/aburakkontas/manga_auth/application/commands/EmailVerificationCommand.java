package com.aburakkontas.manga_auth.application.commands;

import com.aburakkontas.manga_auth.application.validators.Validatable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class EmailVerificationCommand extends Validatable {
    private String verificationId;
    private String oneTimeCode;
}
