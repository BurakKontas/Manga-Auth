package com.aburakkontas.manga_auth.application.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoutCommand {
    private String refreshToken;
}
