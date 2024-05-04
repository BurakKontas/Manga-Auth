package com.aburakkontas.manga_auth.application.aggregates;

import com.aburakkontas.manga_auth.application.commands.ForgotPasswordCommand;
import com.aburakkontas.manga_auth.application.events.ForgotPasswordEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class ForgotPasswordAggregate {

    @AggregateIdentifier
    private String id;
    private String email;

    public ForgotPasswordAggregate() {
    }

    @CommandHandler
    public ForgotPasswordAggregate(ForgotPasswordCommand forgotPasswordCommand) {
        forgotPasswordCommand.throwIfInvalid();

        var forgotPasswordEvent = new ForgotPasswordEvent();
        forgotPasswordEvent.setEmail(forgotPasswordCommand.getEmail());

        AggregateLifecycle.apply(forgotPasswordEvent);
    }

    @EventSourcingHandler
    public void on(ForgotPasswordEvent forgotPasswordEvent) {
        this.id = UUID.randomUUID().toString();
        this.email = forgotPasswordEvent.getEmail();
    }
}
