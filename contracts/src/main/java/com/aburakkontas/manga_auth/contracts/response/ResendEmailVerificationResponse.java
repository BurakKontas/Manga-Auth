package com.aburakkontas.manga_auth.contracts.response;

import lombok.Data;

@Data
public class ResendEmailVerificationResponse {
    private String registrationId;
}
