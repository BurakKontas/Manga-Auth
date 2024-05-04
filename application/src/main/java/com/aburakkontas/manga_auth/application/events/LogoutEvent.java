package com.aburakkontas.manga_auth.application.events;

import lombok.Data;

@Data
public class LogoutEvent {
    private String refreshToken;
}
