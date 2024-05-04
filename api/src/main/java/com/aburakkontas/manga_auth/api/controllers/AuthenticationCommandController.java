package com.aburakkontas.manga_auth.api.controllers;

import com.aburakkontas.manga_auth.contracts.request.*;
import com.aburakkontas.manga_auth.contracts.response.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/v1/auth")
public class AuthenticationCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public AuthenticationCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //        var command = new RegisterCommand("aburakkontas@hotmail.com", "Arda", "Kontas", "Password123.");
//
//        String returnValue;
//
//        try {
//            returnValue = commandGateway.sendAndWait(command);
//        } catch (Exception e) {
//            returnValue = e.getLocalizedMessage();
//        }
//
//        return returnValue;

    @PostMapping("/change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(new ChangePasswordResponse());
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestBody LogoutRequest logoutRequest) {
        return ResponseEntity.ok(new LogoutResponse());
    }

    @PostMapping("/email-verification")
    public ResponseEntity<EmailVerificationResponse> emailVerification(@RequestBody EmailVerificationRequest emailVerificationRequest) {
        return ResponseEntity.ok(new EmailVerificationResponse());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return ResponseEntity.ok(new ForgotPasswordResponse());
    }

    @PostMapping("/resend-email-verification")
    public ResponseEntity<ResendEmailVerificationResponse> resendEmailVerification(@RequestBody ResendEmailVerificationRequest resendEmailVerificationRequest) {
        return ResponseEntity.ok(new ResendEmailVerificationResponse());
    }

}