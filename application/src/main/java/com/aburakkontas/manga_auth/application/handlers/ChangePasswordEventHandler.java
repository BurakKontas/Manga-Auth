package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.application.events.ChangePasswordEvent;
import com.aburakkontas.manga_auth.domain.dtos.PasswordChangeDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordEventHandler {

    private final AuthRepository authRepository;

    public ChangePasswordEventHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @EventHandler
    public void handle(ChangePasswordEvent password) {
        var email = password.getEmail();
        var currentPassword = password.getCurrentPassword();
        var newPassword = password.getNewPassword();

        var passwordChangeDTO = new PasswordChangeDTO(email, currentPassword, newPassword);

        authRepository.changePassword(passwordChangeDTO);
    }

}
