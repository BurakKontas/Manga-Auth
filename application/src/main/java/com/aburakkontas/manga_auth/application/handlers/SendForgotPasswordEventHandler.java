package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.domain.dtos.SendPasswordChangeEmailDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga.common.auth.events.SendForgotPasswordEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class SendForgotPasswordEventHandler {

    private final AuthRepository authRepository;

    public SendForgotPasswordEventHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @EventHandler
    public void handle(SendForgotPasswordEvent forgotPasswordEvent) {
        var email = forgotPasswordEvent.getEmail();

        var forgotPasswordDto = new SendPasswordChangeEmailDTO(email);

        authRepository.sendPasswordChangeEmail(forgotPasswordDto);
    }
}
