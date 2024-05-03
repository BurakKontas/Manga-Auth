package com.aburakkontas.manga_auth.application.commands;

import an.awesome.pipelinr.Command;

public record RegisterCommand(String email, String password, String username, String fullName) implements Command<String> {
}
