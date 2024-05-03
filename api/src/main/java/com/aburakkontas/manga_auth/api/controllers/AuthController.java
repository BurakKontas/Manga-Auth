package com.aburakkontas.manga_auth.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.aburakkontas.manga_auth.application.aggregates.RegistrationAggregate;
import com.aburakkontas.manga_auth.application.commands.ChangePasswordCommand;
import com.aburakkontas.manga_auth.application.commands.Ping;
import com.aburakkontas.manga_auth.application.commands.RegisterCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController {

    private final CommandGateway commandGateway;

    @Autowired
    public AuthController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/login")
    public String login(@RequestBody String username) {
        var command = new RegisterCommand("aburakkontas@hotmail.com", "Arda", "Kontas", "Password123.");

        String returnValue;

        try {
            returnValue = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }

        return returnValue;
    }

    @PostMapping("/register-user")
    public String register(@RequestBody String username) {
        return "User registered successfully!";
    }

    @PutMapping("/change-password")
    public boolean changePassword(@RequestBody String username) {
        return true;
    }
}