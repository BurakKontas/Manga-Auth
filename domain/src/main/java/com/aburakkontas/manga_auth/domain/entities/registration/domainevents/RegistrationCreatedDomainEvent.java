package com.aburakkontas.manga_auth.domain.entities.registration.domainevents;

import com.aburakkontas.manga_auth.domain.entities.registration.valueobjects.Email;
import com.aburakkontas.manga_auth.domain.primitives.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RegistrationCreatedDomainEvent extends DomainEvent {
    private final Email email;

    public RegistrationCreatedDomainEvent(UUID id, Email email) {
        super(id);
        this.email = email;
    }
}