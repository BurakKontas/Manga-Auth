package com.aburakkontas.manga_auth.contracts.request;

import lombok.Data;

@Data
public class ValidateTokenRequest {
    private String token;
}
