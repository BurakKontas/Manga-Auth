package com.aburakkontas.manga_auth.api.controllers;

import com.aburakkontas.manga_auth.application.commands.ResendEmailVerificationQuery;
import com.aburakkontas.manga_auth.application.queries.LoginQuery;
import com.aburakkontas.manga_auth.application.queries.RegisterQuery;
import com.aburakkontas.manga_auth.application.queries.results.LoginQueryResult;
import com.aburakkontas.manga_auth.application.queries.results.RegisterQueryResult;
import com.aburakkontas.manga_auth.application.queries.results.ResendEmailVerificationQueryResult;
import com.aburakkontas.manga_auth.contracts.request.*;
import com.aburakkontas.manga_auth.contracts.response.*;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthenticationQueryController {

    private QueryGateway queryGateway;

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
        return ResponseEntity.ok(new RoleCheckResponse());
    }

    @PostMapping("/validate-token")
    public ResponseEntity<ValidateTokenResponse> validateToken(@RequestBody ValidateTokenRequest roleCheckRequest) {
        return ResponseEntity.ok(new ValidateTokenResponse());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(new RefreshTokenResponse());
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