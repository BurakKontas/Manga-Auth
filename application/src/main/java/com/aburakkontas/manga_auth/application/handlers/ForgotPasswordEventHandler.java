package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga.common.auth.events.ForgotPasswordEvent;
import com.aburakkontas.manga_auth.domain.dtos.ForgotPasswordDTO;
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
        var changePasswordId = forgotPasswordEvent.getChangePasswordId();
        var newPassword = forgotPasswordEvent.getNewPassword();

        var forgotPasswordDto = new ForgotPasswordDTO(changePasswordId, newPassword);

        authRepository.forgotPassword(forgotPasswordDto);
    }
}
