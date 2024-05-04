package com.aburakkontas.manga_auth.application.queries;

import an.awesome.pipelinr.Command;

public record RefreshTokenQuery(String refreshToken) implements Command<String> {
}
