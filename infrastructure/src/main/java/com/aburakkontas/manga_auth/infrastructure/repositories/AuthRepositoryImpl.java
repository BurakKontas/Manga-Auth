package com.aburakkontas.manga_auth.infrastructure.repositories;

import com.aburakkontas.manga_auth.domain.dtos.*;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga_auth.infrastructure.clients.FusionClient;
import com.aburakkontas.manga_auth.infrastructure.configs.FusionConfig;
import io.fusionauth.domain.User;
import io.fusionauth.domain.UserRegistration;
import io.fusionauth.domain.api.LoginRequest;
import io.fusionauth.domain.api.identityProvider.IdentityProviderLoginRequest;
import io.fusionauth.domain.api.identityProvider.IdentityProviderStartLoginRequest;
import io.fusionauth.domain.api.jwt.RefreshRequest;
import io.fusionauth.domain.api.passwordless.PasswordlessLoginRequest;
import io.fusionauth.domain.api.passwordless.PasswordlessStartRequest;
import io.fusionauth.domain.api.user.ChangePasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordRequest;
import io.fusionauth.domain.api.user.RegistrationRequest;
import io.fusionauth.domain.api.user.VerifyRegistrationRequest;
import io.fusionauth.domain.provider.IdentityProviderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthRepositoryImpl implements AuthRepository {

    private final FusionClient fusionClient;
    private final UUID applicationId;

    @Autowired
    public AuthRepositoryImpl(FusionClient fusionClient) {
        this.fusionClient = fusionClient;
        this.applicationId = UUID.fromString(FusionConfig.applicationId);
    }

    @Override
    public RegisterResultDTO register(RegisterDTO registerDTO) {
        //TODO: Only Testing Purposes
        var ifUserExists = fusionClient.getClient().retrieveUserByEmail(registerDTO.getEmail());

        if(ifUserExists.wasSuccessful()) {
            fusionClient.getClient().deleteUser(ifUserExists.getSuccessResponse().user.id);
        }
        //TODO: Only Testing Purposes

        var user = new User();
        user.email = registerDTO.getEmail();
        user.firstName = registerDTO.getFirstName();
        user.lastName = registerDTO.getLastName();
        user.password = registerDTO.getHashPassword();

        var userRegistration = new UserRegistration();
        userRegistration.applicationId = applicationId;
        userRegistration.username = registerDTO.getEmail();

        var registerRequest = new RegistrationRequest();
        registerRequest.registration = userRegistration;
        registerRequest.user = user;
        registerRequest.skipVerification = false;
        registerRequest.sendSetPasswordEmail = false;

        var response = fusionClient.getClient().register(null, registerRequest);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to register user");
        }

        return new RegisterResultDTO(response.getSuccessResponse().registrationVerificationId);
    }

    @Override
    public LoginResultDTO login(LoginDTO loginDTO) {
        var loginRequest = new LoginRequest();
        loginRequest.applicationId = applicationId;
        loginRequest.loginId = loginDTO.getEmail();
        loginRequest.password = loginDTO.getPassword();

        var response = fusionClient.getClient().login(loginRequest);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to login user");
        }

        var isVerified = response.getSuccessResponse().user.getRegistrationForApplication(applicationId).verified;

        if(!isVerified) {
            throw new RuntimeException("User is not verified");
        }

        return new LoginResultDTO(response.getSuccessResponse().token, response.getSuccessResponse().refreshToken);
    }

    @Override
    public LogoutResultDTO logout(LogoutDTO logoutDTO) {
        var response = fusionClient.getClient().logout(false, logoutDTO.getRefreshToken());

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to logout user");
        }

        return new LogoutResultDTO(true);
    }

    @Override
    public PasswordChangeResultDTO changePassword(PasswordChangeDTO passwordChangeDTO) {
        var changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.applicationId = applicationId;
        changePasswordRequest.currentPassword = passwordChangeDTO.getPassword();
        changePasswordRequest.password = passwordChangeDTO.getNewPassword();
        changePasswordRequest.loginId = passwordChangeDTO.getEmail();

        var response = fusionClient.getClient().changePasswordByIdentity(changePasswordRequest);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to change password");
        }

        return new PasswordChangeResultDTO(true);
    }

    @Override
    public SendPasswordChangeEmailResultDTO sendPasswordChangeEmail(SendPasswordChangeEmailDTO sendPasswordChangeEmail) {
        var email = sendPasswordChangeEmail.getEmail();

        var forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.applicationId = applicationId;
        forgotPasswordRequest.loginId = email;
        forgotPasswordRequest.sendForgotPasswordEmail = true;

        var response = fusionClient.getClient().forgotPassword(forgotPasswordRequest);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to send password change email");
        }

        return new SendPasswordChangeEmailResultDTO(true);
    }

    @Override
    public ResendEmailVerificationResultDTO resendEmailVerification(ResendEmailVerificationDTO resendEmailVerificationDTO) {
        var email = resendEmailVerificationDTO.getEmail();
        var response = fusionClient.getClient().resendRegistrationVerification(email, applicationId);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to send password change email");
        }

        return new ResendEmailVerificationResultDTO(response.getSuccessResponse().verificationId);
    }

    @Override
    public HasRoleResultDTO hasRole(HasRoleDTO hasRoleDTO) {
        var email = hasRoleDTO.getEmail();
        var user = fusionClient.getClient().retrieveUserByLoginId(email);

        if(!user.wasSuccessful()) {
            throw new RuntimeException("Failed to retrieve user");
        }

        var registration = fusionClient.getClient().retrieveRegistration(user.getSuccessResponse().user.id, applicationId);

        if(!registration.wasSuccessful()) {
            throw new RuntimeException("Failed to retrieve registration");
        }

        var hasRole = registration.getSuccessResponse().registration.roles.contains(hasRoleDTO.getRoleName());

        return new HasRoleResultDTO(hasRole, email);
    }

    @Override
    public ValidateTokenResultDTO validateToken(ValidateTokenDTO validateTokenDTO) {
        var response = fusionClient.getClient().validateJWT(validateTokenDTO.getToken());

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to validate token");
        }

        return new ValidateTokenResultDTO(!response.getSuccessResponse().jwt.isExpired());
    }

    @Override
    public VerifyRegistrationWithCodeResultDTO verifyRegistrationWithCode(VerifyRegistrationWithCodeDTO verifyRegistrationWithCodeDTO) {
        var verifyRegistrationRequest = new VerifyRegistrationRequest();
        verifyRegistrationRequest.oneTimeCode = verifyRegistrationWithCodeDTO.getCode();
        verifyRegistrationRequest.verificationId = verifyRegistrationWithCodeDTO.getVerificationId();

        var response = fusionClient.getClient().verifyUserRegistration(verifyRegistrationRequest);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to verify registration");
        }

        return new VerifyRegistrationWithCodeResultDTO(true);
    }

    @Override
    public RefreshTokenResultDTO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        var refreshRequest = new RefreshRequest();
        refreshRequest.refreshToken = refreshTokenDTO.getRefreshToken();

        var response = fusionClient.getClient().exchangeRefreshTokenForJWT(refreshRequest);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to refresh token");
        }

        return new RefreshTokenResultDTO(response.getSuccessResponse().token, response.getSuccessResponse().refreshToken);
    }

    @Override
    public GenerateGoogleUriResultDTO generateGoogleUri() {
        String urlBuilder = FusionConfig.GoogleBaseUri +
                "?client_id=" + FusionConfig.GoogleClientId +
                "&redirect_uri=" + FusionConfig.oauth2RedirectUri +
                "&response_type=" + FusionConfig.GoogleResponseType +
                "&scope=" + FusionConfig.GoogleScope +
                "&access_type=" + FusionConfig.GoogleAccessType +
                "&prompt=" + FusionConfig.GooglePrompt +
                "&flowName=" + FusionConfig.GoogleFlowName +
                "&service=" + FusionConfig.GoogleService +
                "&ddm=" + FusionConfig.GoogleDDM +
                "&o2v=" + FusionConfig.GoogleO2V;

        return new GenerateGoogleUriResultDTO(urlBuilder);
    }

    @Override
    public ExchangeOAuth2CodeForJWTResultDTO exchangeOAuth2CodeForJWT(ExchangeOAuth2CodeForJWTDTO exchangeOAuth2CodeForJWTDTO) {
        var redirectUri = FusionConfig.oauth2RedirectUri;
        var code = exchangeOAuth2CodeForJWTDTO.getCode();
        var google = fusionClient.getClient().retrieveIdentityProviderByType(IdentityProviderType.Google);

        if(!google.wasSuccessful()) {
            throw new RuntimeException("Failed to retrieve Google Identity Provider");
        }

        var googleId = google.getSuccessResponse().identityProviders.stream().findFirst().orElseThrow().id;

        var req = new IdentityProviderLoginRequest();
        req.identityProviderId = googleId;
        req.applicationId = applicationId;
        req.data = new HashMap<>() {
            {
                put("code", code);
                put("redirect_uri", redirectUri);
            }
        };

        var response = fusionClient.getClient().identityProviderLogin(req);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to exchange OAuth2 code for JWT");
        }

        return new ExchangeOAuth2CodeForJWTResultDTO(response.getSuccessResponse().token, response.getSuccessResponse().refreshToken);
    }

    @Override
    public ForgotPasswordResultDTO forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        var changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.changePasswordId = forgotPasswordDTO.getChangePasswordId();
        changePasswordRequest.password = forgotPasswordDTO.getNewPassword();

        var response = fusionClient.getClient().changePasswordByIdentity(changePasswordRequest);

        if(!response.wasSuccessful()) {
            throw new RuntimeException("Failed to change password");
        }

        return new ForgotPasswordResultDTO(true);
    }
}
