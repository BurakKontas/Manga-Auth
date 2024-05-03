package com.aburakkontas.manga_auth.domain.primitives;

import an.awesome.pipelinr.Notification;

import java.util.UUID;

public class DomainEvent implements Notification {
    private final UUID id;

    public DomainEvent(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}