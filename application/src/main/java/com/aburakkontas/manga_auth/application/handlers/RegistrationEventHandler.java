package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.application.events.RegistrationCreatedEvent;
import com.aburakkontas.manga_auth.domain.dtos.RegisterDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

//@Component
//public class RegistrationEventHandler {
//
//    private final AuthRepository authRepository;
//
//    public RegistrationEventHandler(AuthRepository authRepository) {
//        this.authRepository = authRepository;
//    }
//
//    @EventHandler
//    public void on(RegistrationCreatedEvent registrationCreatedEvent) {
//
//        var email = registrationCreatedEvent.getEmail();
//        var firstName = registrationCreatedEvent.getFirstName();
//        var lastName = registrationCreatedEvent.getLastName();
//        var password = registrationCreatedEvent.getPassword();
//        var createRegistrationDTO = new RegisterDTO(email, firstName, lastName, password);
//        authRepository.register(createRegistrationDTO);
//    }
//}
