package com.aburakkontas.manga_auth.contracts.request;

import lombok.Data;

@Data
public class EmailVerificationRequest {
    private String registrationId;
    private String oneTimeCode;
}
