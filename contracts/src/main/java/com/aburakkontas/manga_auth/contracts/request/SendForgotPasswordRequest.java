package com.aburakkontas.manga_auth.contracts.request;

import lombok.Data;

@Data
public class SendForgotPasswordRequest {
    private String email;
}
