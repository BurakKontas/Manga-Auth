package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.application.events.EmailVerifiedEvent;
import com.aburakkontas.manga_auth.domain.dtos.VerifyRegistrationWithCodeDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class EmailVerifiedEventHandler {

    private final AuthRepository authRepository;

    public EmailVerifiedEventHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    @EventHandler
    public void on(EmailVerifiedEvent emailVerifiedEvent) {
        var verificationId = emailVerifiedEvent.getVerificationId();
        var oneTimeCode = emailVerifiedEvent.getOneTimeCode();

        var verifyRegistrationWithCodeDto = new VerifyRegistrationWithCodeDTO(verificationId, oneTimeCode);

        authRepository.verifyRegistrationWithCode(verifyRegistrationWithCodeDto);
    }

}
