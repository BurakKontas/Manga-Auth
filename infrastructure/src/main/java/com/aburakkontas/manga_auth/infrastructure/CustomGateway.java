package com.aburakkontas.manga_auth.infrastructure;

import org.axonframework.commandhandling.gateway.Timeout;

import java.util.concurrent.TimeUnit;

public interface CustomGateway {

    void sendCommand(Object command);

    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    Object sendCommandAndWaitForAResult(Object command);
}
