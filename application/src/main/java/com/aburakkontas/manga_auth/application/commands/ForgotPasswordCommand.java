package com.aburakkontas.manga_auth.application.commands;

import an.awesome.pipelinr.Command;

public record ForgotPasswordCommand(String email) implements Command<String> {
}
