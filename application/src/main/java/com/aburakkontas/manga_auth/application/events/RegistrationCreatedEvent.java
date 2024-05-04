package com.aburakkontas.manga_auth.application.events;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RegistrationCreatedEvent {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
