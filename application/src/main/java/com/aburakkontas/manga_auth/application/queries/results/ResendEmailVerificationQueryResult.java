package com.aburakkontas.manga_auth.application.queries.results;

import lombok.Data;

@Data
public class ResendEmailVerificationQueryResult {
    private String registrationId;
}
