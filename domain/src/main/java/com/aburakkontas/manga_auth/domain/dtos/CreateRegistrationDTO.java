package com.aburakkontas.manga_auth.domain.dtos;

import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.Email;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.FirstName;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.HashPassword;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.LastName;

public class CreateRegistrationDTO {
    private Email email;
    private FirstName firstName;
    private LastName lastName;
    private HashPassword hashPassword;

    private CreateRegistrationDTO(Email email, FirstName firstName, LastName lastName, HashPassword hashPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashPassword = hashPassword;
    }

    public static CreateRegistrationDTO create(Email email, FirstName firstName, LastName lastName, HashPassword hashPassword) {
        return new CreateRegistrationDTO(email, firstName, lastName, hashPassword);
    }

    public Email getEmail() {
        return email;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public HashPassword getHashPassword() {
        return hashPassword;
    }
}
