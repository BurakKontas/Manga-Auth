package com.aburakkontas.manga_auth.application.events;

import lombok.Data;

import java.util.UUID;

@Data
public class EmailVerifiedEvent {
    private String verificationId;
    private String oneTimeCode;
}
