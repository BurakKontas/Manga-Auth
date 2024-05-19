package com.aburakkontas.manga_auth.infrastructure.repositories;

import com.aburakkontas.manga_auth.domain.dtos.*;
import com.aburakkontas.manga_auth.domain.exceptions.ErrorCodes;
import com.aburakkontas.manga_auth.domain.exceptions.ExceptionWithErrorCode;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga_auth.infrastructure.clients.FusionClient;
import com.aburakkontas.manga_auth.infrastructure.configs.FusionConfig;
import io.fusionauth.domain.User;
import io.fusionauth.domain.UserRegistration;
import io.fusionauth.domain.api.LoginRequest;
import io.fusionauth.domain.api.identityProvider.IdentityProviderLoginRequest;
import io.fusionauth.domain.api.jwt.RefreshRequest;
import io.fusionauth.domain.api.user.ChangePasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordRequest;
import io.fusionauth.domain.api.user.RegistrationRequest;
import io.fusionauth.domain.api.user.VerifyRegistrationRequest;
import io.fusionauth.domain.provider.IdentityProviderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
        var ifUserExists = fusionClient.getClient().retrieveUserByEmail(registerDTO.getEmail());

        if(ifUserExists.wasSuccessful()) {
//            fusionClient.getClient().deleteUser(ifUserExists.getSuccessResponse().user.id);

            throw new ExceptionWithErrorCode("User already exists", ErrorCodes.USER_ALREADY_EXISTS);
        }

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
            throw new ExceptionWithErrorCode("Failed to register user", ErrorCodes.FAILED_TO_REGISTER_USER);
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
            throw new ExceptionWithErrorCode("User not found or password is incorrect", ErrorCodes.USER_NOT_FOUND_OR_PASSWORD_INCORRECT);
        }

        var isVerified = response.getSuccessResponse().user.getRegistrationForApplication(applicationId).verified;

        if(!isVerified) {
            throw new ExceptionWithErrorCode("User is not verified", ErrorCodes.USER_IS_NOT_VERIFIED);
        }

        return new LoginResultDTO(response.getSuccessResponse().token, response.getSuccessResponse().refreshToken);
    }

    @Override
    public LogoutResultDTO logout(LogoutDTO logoutDTO) {
        var response = fusionClient.getClient().logout(false, logoutDTO.getRefreshToken());

        if(!response.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to logout user", ErrorCodes.FAILED_TO_LOGOUT_USER);
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
            throw new ExceptionWithErrorCode("Failed to change password", ErrorCodes.FAILED_TO_CHANGE_PASSWORD);
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
            throw new ExceptionWithErrorCode("Failed to send password change email", ErrorCodes.FAILED_TO_SEND_PASSWORD_CHANGE_EMAIL);
        }

        return new SendPasswordChangeEmailResultDTO(true);
    }

    @Override
    public ResendEmailVerificationResultDTO resendEmailVerification(ResendEmailVerificationDTO resendEmailVerificationDTO) {
        var email = resendEmailVerificationDTO.getEmail();
        var response = fusionClient.getClient().resendRegistrationVerification(email, applicationId);

        if(!response.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to send password change email", ErrorCodes.FAILED_TO_SEND_PASSWORD_CHANGE_EMAIL);
        }

        return new ResendEmailVerificationResultDTO(response.getSuccessResponse().verificationId);
    }

    @Override
    public HasRoleResultDTO hasRole(HasRoleDTO hasRoleDTO) {
        var email = hasRoleDTO.getEmail();
        var user = fusionClient.getClient().retrieveUserByLoginId(email);

        if(!user.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to retrieve user", ErrorCodes.FAILED_TO_RETRIEVE_USER);
        }

        var registration = fusionClient.getClient().retrieveRegistration(user.getSuccessResponse().user.id, applicationId);

        if(!registration.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to retrieve registration", ErrorCodes.FAILED_TO_RETRIEVE_USER);
        }

        var hasRole = registration.getSuccessResponse().registration.roles.contains(hasRoleDTO.getRoleName());

        return new HasRoleResultDTO(hasRole, email);
    }

    @Override
    public ValidateTokenResultDTO validateToken(ValidateTokenDTO validateTokenDTO) {
        var response = fusionClient.getClient().validateJWT(validateTokenDTO.getToken());

        if(!response.wasSuccessful() && response.status == 401) {
            return new ValidateTokenResultDTO(false);
        }

        if(!response.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to validate token", ErrorCodes.FAILED_TO_VALIDATE_TOKEN);
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
            throw new ExceptionWithErrorCode("Failed to verify registration", ErrorCodes.FAILED_TO_VERIFY_EMAIL);
        }

        return new VerifyRegistrationWithCodeResultDTO(true);
    }

    @Override
    public RefreshTokenResultDTO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        var refreshRequest = new RefreshRequest();
        refreshRequest.refreshToken = refreshTokenDTO.getRefreshToken();

        var response = fusionClient.getClient().exchangeRefreshTokenForJWT(refreshRequest);

        if(!response.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to refresh token", ErrorCodes.FAILED_TO_REFRESH_TOKEN);
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
            throw new ExceptionWithErrorCode("Failed to retrieve Google Identity Provider", ErrorCodes.FAILED_TO_RETRIEVE_GOOGLE_IDENTITY_PROVIDER);
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
            throw new ExceptionWithErrorCode("Failed to exchange OAuth2 code for JWT", ErrorCodes.FAILED_TO_EXCHANGE_OAUTH2_CODE_FOR_JWT);
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
            throw new ExceptionWithErrorCode("Failed to change password", ErrorCodes.FAILED_TO_CHANGE_PASSWORD);
        }

        return new ForgotPasswordResultDTO(true);
    }

    @Override
    public GetAllErrorCodesResultDTO getAllErrorCodes() {
        var errors = ErrorCodes.class.getDeclaredFields();
        var errorCodes = new HashMap<String, Integer>();

        for(var error : errors) {
            try {
                errorCodes.put(error.getName(), (Integer) error.get(null));
            } catch (IllegalAccessException e) {
                throw new ExceptionWithErrorCode("Mapping Error", ErrorCodes.MAPPING_ERROR);
            }
        }

        return new GetAllErrorCodesResultDTO(errorCodes);
    }

    @Override
    public GetUserDetailsFromTokenResultDTO getUserDetailsFromToken(GetUserDetailsFromTokenDTO getUserDetailsDTO) {
        var token = getUserDetailsDTO.getToken();
        var tokenDetails = fusionClient.getClient().retrieveUserInfoFromAccessToken(token);

        if(!tokenDetails.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to extract token", ErrorCodes.FAILED_TO_EXTRACT_TOKEN);
        }

        var user = fusionClient.getClient().retrieveUserByLoginId(tokenDetails.getSuccessResponse().get("email").toString());

        if(!user.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to retrieve user", ErrorCodes.FAILED_TO_RETRIEVE_USER);
        }

        var registration = user.getSuccessResponse().user.getRegistrationForApplication(applicationId);

        if(registration == null) {
            throw new ExceptionWithErrorCode("Failed to retrieve registration", ErrorCodes.FAILED_TO_RETRIEVE_USER);
        }

        var email = user.getSuccessResponse().user.email;
        var firstName = user.getSuccessResponse().user.firstName;
        var lastName = user.getSuccessResponse().user.lastName;
        var permissions = new ArrayList<String>(registration.roles);
        var username = registration.username;
        var userId = registration.id;
        var lastLogin = registration.lastLoginInstant;

        return new GetUserDetailsFromTokenResultDTO(email, firstName, lastName, permissions, username, userId, lastLogin);
    }

    @Override
    public GetUserDetailsByIdResultDTO getUserDetailsById(GetUserDetailsByIdDTO getUserDetailsByIdDTO) {
        var user = fusionClient.getClient().retrieveUser(getUserDetailsByIdDTO.getUserId());

        if(!user.wasSuccessful()) {
            throw new ExceptionWithErrorCode("Failed to retrieve user", ErrorCodes.FAILED_TO_RETRIEVE_USER);
        }

        var registration = user.getSuccessResponse().user.getRegistrationForApplication(applicationId);

        if(registration == null) {
            throw new ExceptionWithErrorCode("Failed to retrieve registration", ErrorCodes.FAILED_TO_RETRIEVE_USER);
        }

        var email = user.getSuccessResponse().user.email;
        var firstName = user.getSuccessResponse().user.firstName;
        var lastName = user.getSuccessResponse().user.lastName;
        var permissions = new ArrayList<String>(registration.roles);
        var username = registration.username;
        var userId = registration.id;
        var lastLogin = registration.lastLoginInstant;

        return new GetUserDetailsByIdResultDTO(email, firstName, lastName, permissions, username, userId, lastLogin);


    }
}
