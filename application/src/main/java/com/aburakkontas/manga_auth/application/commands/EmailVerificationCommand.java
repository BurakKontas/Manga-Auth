package com.aburakkontas.manga_auth.application.commands;

import an.awesome.pipelinr.Command;

public record EmailVerificationCommand(String verificationId, String oneTimeCode) implements Command<String> {
}
