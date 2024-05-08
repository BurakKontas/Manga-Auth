package com.aburakkontas.manga_auth.application.aggregates;

import com.aburakkontas.manga_auth.application.commands.SendForgotPasswordCommand;
import com.aburakkontas.manga_auth.application.events.SendForgotPasswordEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class SendForgotPasswordAggregate {

    @AggregateIdentifier
    private String id;
    private String email;

    public SendForgotPasswordAggregate() {
    }

    @CommandHandler
    public SendForgotPasswordAggregate(SendForgotPasswordCommand forgotPasswordCommand) {
        forgotPasswordCommand.throwIfInvalid();

        var forgotPasswordEvent = new SendForgotPasswordEvent();
        forgotPasswordEvent.setEmail(forgotPasswordCommand.getEmail());

        AggregateLifecycle.apply(forgotPasswordEvent);
    }

    @EventSourcingHandler
    public void on(SendForgotPasswordEvent sendForgotPasswordEvent) {
        this.id = UUID.randomUUID().toString();
        this.email = sendForgotPasswordEvent.getEmail();
    }
}