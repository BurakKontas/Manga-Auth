package com.aburakkontas.manga_auth.application.commands;

import an.awesome.pipelinr.Command;

public record RefreshTokenCommand(String refreshToken) implements Command<String> {
}
