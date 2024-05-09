package com.aburakkontas.manga_auth.api.controllers;

import com.aburakkontas.manga_auth.application.commands.ResendEmailVerificationQuery;
import com.aburakkontas.manga_auth.application.queries.*;
import com.aburakkontas.manga_auth.application.queries.results.*;
import com.aburakkontas.manga_auth.contracts.request.*;
import com.aburakkontas.manga_auth.contracts.response.*;
import com.aburakkontas.manga_auth.domain.primitives.Result;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationQueryController {

    private final QueryGateway queryGateway;

    @Autowired
    public AuthenticationQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @PostMapping("/login")
    public ResponseEntity<Result<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        var query = LoginQuery.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();

        var result = queryGateway.query(query, LoginQueryResult.class).join();
        var response = new LoginResponse();
        BeanUtils.copyProperties(result, response);

        return ResponseEntity.ok(Result.success(response));
    }

    @PostMapping("/register")
    public ResponseEntity<Result<RegisterResponse>> register(@RequestBody RegisterRequest registerRequest) {
        var query = RegisterQuery.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();

        var result = queryGateway.query(query, RegisterQueryResult.class).join();
        var response = new RegisterResponse();
        response.setRegistrationId(result.getRegistrationId());

        return ResponseEntity.ok(Result.success(response));
    }

    @PostMapping("/role-check")
    public ResponseEntity<Result<RoleCheckResponse>> roleCheck(@RequestBody RoleCheckRequest roleCheckRequest) {
        var query = RoleCheckQuery.builder()
                .email(roleCheckRequest.getEmail())
                .role(roleCheckRequest.getRole())
                .build();

        var result = queryGateway.query(query, RoleCheckQueryResult.class).join();

        var response = new RoleCheckResponse();
        response.setHasRole(result.isHasRole());

        return ResponseEntity.ok(Result.success(response));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Result<ValidateTokenResponse>> validateToken(@RequestBody ValidateTokenRequest validateTokenRequest) {
        var query = ValidateTokenQuery.builder()
                .token(validateTokenRequest.getToken())
                .build();

        var result = queryGateway.query(query, ValidateTokenQueryResult.class).join();

        var response = new ValidateTokenResponse();
        response.setValid(result.isValid());

        return ResponseEntity.ok(Result.success(response));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Result<RefreshTokenResponse>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        var query = RefreshTokenQuery.builder()
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .build();

        var result = queryGateway.query(query, RefreshTokenQueryResult.class).join();

        var response = new RefreshTokenResponse();
        response.setToken(result.getToken());
        response.setRefreshToken(result.getRefreshToken());

        return ResponseEntity.ok(Result.success(response));
    }

    @PostMapping("/resend-email-verification")
    public ResponseEntity<Result<ResendEmailVerificationResponse>> resendEmailVerification(@RequestBody ResendEmailVerificationRequest resendEmailVerificationRequest) {
        var query = ResendEmailVerificationQuery.builder()
                .email(resendEmailVerificationRequest.getEmail())
                .build();

        var result = queryGateway.query(query, ResendEmailVerificationQueryResult.class).join();

        var response = new ResendEmailVerificationResponse();
        response.setRegistrationId(result.getRegistrationId());

        return ResponseEntity.ok(Result.success(response));
    }

    @GetMapping("/generate-google-login-uri")
    public ResponseEntity<Result<GenerateGoogleLoginUriResponse>> generateGoogleLoginUri() {
        var query = GenerateGoogleLoginUriQuery.builder()
                .build();

        var result = queryGateway.query(query, GenerateGoogleLoginUriQueryResult.class).join();

        var response = new GenerateGoogleLoginUriResponse();
        response.setUri(result.getUri());

        return ResponseEntity.ok(Result.success(response));
    }

    @GetMapping("/get-all-error-codes")
    public ResponseEntity<Result<GetAllErrorCodesResponse>> getAllErrorCodes() {
        var query = GetAllErrorCodesQuery.builder()
                .build();

        var result = queryGateway.query(query, GetAllErrorCodesQueryResult.class).join();

        var response = new GetAllErrorCodesResponse();
        response.setErrorCodes(result.getErrorCodes());

        return ResponseEntity.ok(Result.success(response));
    }
}
