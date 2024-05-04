package com.aburakkontas.manga_auth.application.commands;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResendEmailVerificationCommand {
    private String email;
}
