package com.aburakkontas.manga_auth.contracts.response;

import lombok.Data;

@Data
public class RefreshTokenResponse {
    private String token;
    private String refreshToken;
}
