package com.aburakkontas.manga_auth.infrastructure.repositories;

import com.aburakkontas.manga_auth.domain.dtos.*;
import com.aburakkontas.manga_auth.domain.entities.registration.Registration;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga_auth.infrastructure.clients.FusionClient;
import io.fusionauth.client.FusionAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthRepositoryImpl implements AuthRepository {

    @Autowired
    private FusionClient fusionClient;

    @Override
    public CreateRegistrationResultDTO createRegistration(CreateRegistrationDTO createRegistrationDTO) {

        var response = fusionClient.getClient().retrieveUserByEmail("hheraeth@gmail.com");
        var registration = Registration.create(createRegistrationDTO);
        return null;
    }

    @Override
    public EmailVerifyResultDTO verifyEmail(EmailVerifyDTO emailVerifyDTO) {
        return null;
    }

    @Override
    public LoginResultDTO login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public LogoutResultDTO logout(LogoutDTO logoutDTO) {
        return null;
    }

    @Override
    public PasswordChangeResultDTO changePassword(PasswordChangeDTO passwordChangeDTO) {
        return null;
    }

    @Override
    public SendPasswordChangeEmailResultDTO sendPasswordChangeEmail(SendPasswordChangeEmailResult passwordChangeEmailSendDTO) {
        return null;
    }

    @Override
    public ResendEmailVerificationResultDTO resendEmailVerification(ResendEmailVerificationDTO sendEmailVerificationDTO) {
        return null;
    }

    @Override
    public HasRoleResultDTO hasRole(HasRoleDTO hasRoleDTO) {
        return null;
    }

    @Override
    public ValidateTokenResultDTO validateToken(ValidateTokenDTO validateTokenDTO) {
        return null;
    }

    @Override
    public VerifyRegistrationResultDTO verifyRegistration(VerifyRegistrationDTO verifyRegistrationDTO) {
        return null;
    }

    @Override
    public VerifyRegistrationWithCodeResultDTO verifyRegistrationWithCode(VerifyRegistrationWithCodeDTO verifyRegistrationWithCodeDTO) {
        return null;
    }
}
