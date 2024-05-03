package com.aburakkontas.manga_auth.domain.repositories;

import com.aburakkontas.manga_auth.domain.dtos.*;

public interface AuthRepository {
    CreateRegistrationResultDTO createRegistration(CreateRegistrationDTO createRegistrationDTO);
    EmailVerifyResultDTO verifyEmail(EmailVerifyDTO emailVerifyDTO);
    LoginResultDTO login(LoginDTO loginDTO);
    LogoutResultDTO logout(LogoutDTO logoutDTO);
    PasswordChangeResultDTO changePassword(PasswordChangeDTO passwordChangeDTO);
    SendPasswordChangeEmailResultDTO sendPasswordChangeEmail(SendPasswordChangeEmailResult passwordChangeEmailSendDTO);
    ResendEmailVerificationResultDTO resendEmailVerification(ResendEmailVerificationDTO sendEmailVerificationDTO);
    HasRoleResultDTO hasRole(HasRoleDTO hasRoleDTO);
    ValidateTokenResultDTO validateToken(ValidateTokenDTO validateTokenDTO);
    VerifyRegistrationResultDTO verifyRegistration(VerifyRegistrationDTO verifyRegistrationDTO);
    VerifyRegistrationWithCodeResultDTO verifyRegistrationWithCode(VerifyRegistrationWithCodeDTO verifyRegistrationWithCodeDTO);
}