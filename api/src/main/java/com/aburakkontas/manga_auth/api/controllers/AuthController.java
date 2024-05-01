package com.aburakkontas.manga_auth.api.controllers;

import an.awesome.pipelinr.Pipeline;
import com.aburakkontas.manga_auth.application.commands.Ping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController {

     private final Pipeline pipeline;

     @Autowired
     public AuthController(Pipeline pipeline) {
         this.pipeline = pipeline;
     }

    @PostMapping("/login")
    public String login(@RequestBody String username) {
        return new Ping(username).execute(pipeline);
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