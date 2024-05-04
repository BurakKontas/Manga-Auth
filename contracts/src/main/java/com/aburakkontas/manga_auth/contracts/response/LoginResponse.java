package com.aburakkontas.manga_auth.contracts.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String refreshToken;

}
