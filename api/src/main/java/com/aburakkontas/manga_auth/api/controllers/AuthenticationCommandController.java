package com.aburakkontas.manga_auth.api.controllers;

import com.aburakkontas.manga_auth.application.commands.*;
import com.aburakkontas.manga_auth.contracts.request.*;
import com.aburakkontas.manga_auth.contracts.response.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public AuthenticationCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        var command = ChangePasswordCommand.builder()
                .email(changePasswordRequest.getEmail())
                .currentPassword(changePasswordRequest.getCurrentPassword())
                .newPassword(changePasswordRequest.getNewPassword())
                .build();

        String returnValue;

        try {
            returnValue = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }

        return returnValue;
    }

    @PostMapping("/logout")
    public String logout(@RequestBody LogoutRequest logoutRequest) {
        var command = LogoutCommand.builder()
                .refreshToken(logoutRequest.getRefreshToken())
                .build();

        String returnValue;

        try {
            returnValue = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }

        return returnValue;
    }

    @PostMapping("/email-verification")
    public String emailVerification(@RequestBody EmailVerificationRequest emailVerificationRequest) {
        var command = EmailVerificationCommand.builder()
                .oneTimeCode(emailVerificationRequest.getOneTimeCode())
                .verificationId(emailVerificationRequest.getRegistrationId())
                .build();

        String returnValue;
        try {
            returnValue = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }
        return returnValue;
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        var command = ForgotPasswordCommand.builder()
                .email(forgotPasswordRequest.getEmail())
                .build();

        String returnValue;

        try {
            returnValue = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }

        return returnValue;
    }

}