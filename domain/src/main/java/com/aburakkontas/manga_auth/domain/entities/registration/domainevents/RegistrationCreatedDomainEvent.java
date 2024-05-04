package com.aburakkontas.manga_auth.domain.entities.registration.domainevents;

import com.aburakkontas.manga_auth.domain.primitives.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RegistrationCreatedDomainEvent extends DomainEvent {
    private final String email;

    public RegistrationCreatedDomainEvent(UUID id, String email) {
        super(id);
        this.email = email;
    }
}