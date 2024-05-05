package com.aburakkontas.manga_auth.contracts.response;

import lombok.Data;

@Data
public class OAuth2CallbackResponse {
    private String token;
    private String refreshToken;
}
