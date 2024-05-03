package com.aburakkontas.manga_auth.domain.entities.registration;

import com.aburakkontas.manga_auth.domain.dtos.CreateRegistrationDTO;
import com.aburakkontas.manga_auth.domain.entities.registration.domainevents.RegistrationCreatedDomainEvent;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.Email;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.FirstName;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.HashPassword;
import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.LastName;
import com.aburakkontas.manga_auth.domain.primitives.ValueEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Registration extends ValueEntity {
    private Email email;
    private FirstName firstName;
    private LastName lastName;
    private HashPassword hashPassword;

    private Registration(@NotNull Email email, @NotNull FirstName firstName, @NotNull LastName lastName, @NotNull HashPassword hashPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashPassword = hashPassword;
    }

    public static Registration create(CreateRegistrationDTO dto) {
        var registration = new Registration(dto.getEmail(), dto.getFirstName(), dto.getLastName(), dto.getHashPassword());

        registration.arise(new RegistrationCreatedDomainEvent(UUID.randomUUID(), dto.getEmail()));

        return registration;
    }
}
