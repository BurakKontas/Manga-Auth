package com.aburakkontas.manga_auth.application.queries;

import com.aburakkontas.manga_auth.application.validators.Validatable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class EmailVerificationQuery extends Validatable {
    private String verificationId;
    private String oneTimeCode;
}
