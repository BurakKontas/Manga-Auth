package com.aburakkontas.manga_auth.application.queries.results;

import lombok.Data;

@Data
public class LoginQueryResult {
    private String token;
    private String refreshToken;
}
