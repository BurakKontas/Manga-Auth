package com.aburakkontas.manga_auth.application.handlers;

import an.awesome.pipelinr.Command;
import com.aburakkontas.manga_auth.application.commands.ChangePasswordCommand;
import com.aburakkontas.manga_auth.domain.dtos.CreateRegistrationDTO;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.Email;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.FirstName;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.HashPassword;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.LastName;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordCommandHandler implements Command.Handler<ChangePasswordCommand, String> {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public String handle(ChangePasswordCommand command) {
        var email = new Email("email");
        var firstName = new FirstName("first");
        var lastName = new LastName("last");
        var hashPassword = new HashPassword("pass");
        var dto = CreateRegistrationDTO.create(email, firstName, lastName, hashPassword);

        authRepository.createRegistration(dto);
        return "Password changed successfully";
    }
}
