package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.application.events.LogoutEvent;
import com.aburakkontas.manga_auth.domain.dtos.LogoutDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutEventHandler {

    private final AuthRepository authRepository;

    public LogoutEventHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @EventHandler
    public void handle(LogoutEvent logoutEvent) {
        var refreshToken = logoutEvent.getRefreshToken();

        var logoutDto = new LogoutDTO(refreshToken);

        authRepository.logout(logoutDto);
    }
}
