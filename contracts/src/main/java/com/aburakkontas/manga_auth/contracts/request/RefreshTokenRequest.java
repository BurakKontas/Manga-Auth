package com.aburakkontas.manga_auth.contracts.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
