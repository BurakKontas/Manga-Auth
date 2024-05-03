package com.aburakkontas.manga_auth.application.commands;

import an.awesome.pipelinr.Command;

public record ChangePasswordCommand(String email, String currentPassword, String newPassword) implements Command<String> {
}
