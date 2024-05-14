package com.aburakkontas.manga_auth.application.aggregates;

import com.aburakkontas.manga_axon.auth.commands.LogoutCommand;
import com.aburakkontas.manga_axon.auth.events.LogoutEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class LogoutAggregate {

    @AggregateIdentifier
    private String id;
    private String refreshToken;

    public LogoutAggregate() {
    }

    @CommandHandler
    public LogoutAggregate(LogoutCommand logoutCommand) {

        var logoutEvent = new LogoutEvent();
        logoutEvent.setRefreshToken(logoutCommand.getRefreshToken());

        AggregateLifecycle.apply(logoutEvent);
    }

    @EventSourcingHandler
    public void on(LogoutEvent logoutEvent) {
        this.id = UUID.randomUUID().toString();
        this.refreshToken = logoutEvent.getRefreshToken();
    }

}
