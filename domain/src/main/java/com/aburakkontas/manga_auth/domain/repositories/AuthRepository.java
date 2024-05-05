package com.aburakkontas.manga_auth.domain.repositories;

import com.aburakkontas.manga_auth.domain.dtos.*;

public interface AuthRepository {
    RegisterResultDTO register(RegisterDTO createRegistrationDTO);
    LoginResultDTO login(LoginDTO loginDTO);
    LogoutResultDTO logout(LogoutDTO logoutDTO);
    PasswordChangeResultDTO changePassword(PasswordChangeDTO passwordChangeDTO);
    SendPasswordChangeEmailResultDTO sendPasswordChangeEmail(SendPasswordChangeEmailDTO passwordChangeEmailSendDTO);
    ResendEmailVerificationResultDTO resendEmailVerification(ResendEmailVerificationDTO sendEmailVerificationDTO);
    HasRoleResultDTO hasRole(HasRoleDTO hasRoleDTO);
    ValidateTokenResultDTO validateToken(ValidateTokenDTO validateTokenDTO);
    VerifyRegistrationWithCodeResultDTO verifyRegistrationWithCode(VerifyRegistrationWithCodeDTO verifyRegistrationWithCodeDTO);
    RefreshTokenResultDTO refreshToken(RefreshTokenDTO refreshTokenDTO);
    GenerateGoogleUriResultDTO generateGoogleUri();
    ExchangeOAuth2CodeForJWTResultDTO exchangeOAuth2CodeForJWT(ExchangeOAuth2CodeForJWTDTO exchangeOAuth2CodeForJWTDTO);
}