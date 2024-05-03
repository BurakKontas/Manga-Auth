package com.aburakkontas.manga_auth.application.commands;

import an.awesome.pipelinr.Command;

public record LoginCommand(String email, String password) implements Command<String> {
}
