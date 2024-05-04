package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.application.events.ForgotPasswordEvent;
import com.aburakkontas.manga_auth.domain.dtos.SendPasswordChangeEmailDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordEventHandler {

    private final AuthRepository authRepository;

    public ForgotPasswordEventHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @EventHandler
    public void handle(ForgotPasswordEvent forgotPasswordEvent) {
        var email = forgotPasswordEvent.getEmail();

        var forgotPasswordDto = new SendPasswordChangeEmailDTO(email);

        authRepository.sendPasswordChangeEmail(forgotPasswordDto);
    }
}
