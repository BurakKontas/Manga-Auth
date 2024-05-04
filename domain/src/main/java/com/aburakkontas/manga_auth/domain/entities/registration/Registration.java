package com.aburakkontas.manga_auth.domain.entities.registration;

import com.aburakkontas.manga_auth.domain.dtos.RegisterDTO;
import com.aburakkontas.manga_auth.domain.entities.registration.domainevents.RegistrationCreatedDomainEvent;
import com.aburakkontas.manga_auth.domain.primitives.ValueEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Registration extends ValueEntity {
    private String email;
    private String firstName;
    private String lastName;
    private String hashPassword;

    public static Registration create(RegisterDTO dto) {
        var registration = new Registration(dto.getEmail(), dto.getFirstName(), dto.getLastName(), dto.getHashPassword());

        registration.arise(new RegistrationCreatedDomainEvent(UUID.randomUUID(), dto.getEmail()));

        return registration;
    }
}
