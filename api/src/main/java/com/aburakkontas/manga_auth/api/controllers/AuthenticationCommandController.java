package com.aburakkontas.manga_auth.api.controllers;

import com.aburakkontas.manga_auth.application.commands.*;
import com.aburakkontas.manga_auth.contracts.request.*;
import com.aburakkontas.manga_auth.domain.primitives.Result;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result<Object> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        var command = ChangePasswordCommand.builder()
                .email(changePasswordRequest.getEmail())
                .currentPassword(changePasswordRequest.getCurrentPassword())
                .newPassword(changePasswordRequest.getNewPassword())
                .build();

        commandGateway.sendAndWait(command);
        return Result.successWithoutValue();
    }

    @PostMapping("/logout")
    public Result<Object> logout(@RequestBody LogoutRequest logoutRequest) {
        var command = LogoutCommand.builder()
                .refreshToken(logoutRequest.getRefreshToken())
                .build();

        commandGateway.sendAndWait(command);
        return Result.successWithoutValue();
    }

    @PostMapping("/email-verification")
    public Result<Object> emailVerification(@RequestBody EmailVerificationRequest emailVerificationRequest) {
        var command = EmailVerificationCommand.builder()
                .oneTimeCode(emailVerificationRequest.getOneTimeCode())
                .verificationId(emailVerificationRequest.getRegistrationId())
                .build();

        commandGateway.sendAndWait(command);
        return Result.successWithoutValue();
    }

    @PostMapping("/send-forgot-password-email")
    public Result<Object> sendForgotPasswordEmail(@RequestBody SendForgotPasswordRequest sendForgotPasswordRequest) {
        var command = SendForgotPasswordCommand.builder()
                .email(sendForgotPasswordRequest.getEmail())
                .build();

        commandGateway.sendAndWait(command);
        return Result.successWithoutValue();
    }

    @PostMapping("/forgot-password")
    public Result<Object> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        var command = ForgotPasswordCommand.builder()
                .changePasswordId(forgotPasswordRequest.getChangePasswordId())
                .newPassword(forgotPasswordRequest.getNewPassword())
                .build();

        commandGateway.sendAndWait(command);
        return Result.successWithoutValue();
    }

}