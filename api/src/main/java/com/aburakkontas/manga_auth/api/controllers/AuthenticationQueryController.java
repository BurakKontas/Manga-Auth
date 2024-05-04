package com.aburakkontas.manga_auth.api.controllers;

import com.aburakkontas.manga_auth.application.commands.ResendEmailVerificationQuery;
import com.aburakkontas.manga_auth.application.queries.*;
import com.aburakkontas.manga_auth.application.queries.results.*;
import com.aburakkontas.manga_auth.contracts.request.*;
import com.aburakkontas.manga_auth.contracts.response.*;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthenticationQueryController {

    private final QueryGateway queryGateway;

    @Autowired
    public AuthenticationQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var query = LoginQuery.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();

        var result = queryGateway.query(query, LoginQueryResult.class).join();
        var response = new LoginResponse();
        BeanUtils.copyProperties(result, response);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        var query = RegisterQuery.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();

        var result = queryGateway.query(query, RegisterQueryResult.class).join();
        var response = new RegisterResponse();
        response.setRegistrationId(result.getRegistrationId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/role-check")
    public ResponseEntity<RoleCheckResponse> roleCheck(@RequestBody RoleCheckRequest roleCheckRequest) {
        var query = RoleCheckQuery.builder()
                .email(roleCheckRequest.getEmail())
                .role(roleCheckRequest.getRole())
                .build();

        var result = queryGateway.query(query, RoleCheckQueryResult.class).join();

        var response = new RoleCheckResponse();
        response.setHasRole(result.isHasRole());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<ValidateTokenResponse> validateToken(@RequestBody ValidateTokenRequest validateTokenRequest) {
        var query = ValidateTokenQuery.builder()
                .token(validateTokenRequest.getToken())
                .build();

        var result = queryGateway.query(query, ValidateTokenQueryResult.class).join();

        var response = new ValidateTokenResponse();
        response.setValid(result.isValid());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        var query = RefreshTokenQuery.builder()
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .build();

        var result = queryGateway.query(query, RefreshTokenQueryResult.class).join();

        var response = new RefreshTokenResponse();
        response.setToken(result.getToken());
        response.setRefreshToken(result.getRefreshToken());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend-email-verification")
    public ResponseEntity<ResendEmailVerificationResponse> resendEmailVerification(@RequestBody ResendEmailVerificationRequest resendEmailVerificationRequest) {
        var query = ResendEmailVerificationQuery.builder()
                .email(resendEmailVerificationRequest.getEmail())
                .build();

        var result = queryGateway.query(query, ResendEmailVerificationQueryResult.class).join();

        var response = new ResendEmailVerificationResponse();
        response.setRegistrationId(result.getRegistrationId());

        return ResponseEntity.ok(response);
    }
}
