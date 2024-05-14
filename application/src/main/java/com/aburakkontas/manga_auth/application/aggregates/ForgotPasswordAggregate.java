package com.aburakkontas.manga_auth.application.aggregates;

import com.aburakkontas.manga_axon.auth.commands.ForgotPasswordCommand;
import com.aburakkontas.manga_axon.auth.events.ForgotPasswordEvent;
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
    private String changePasswordId;
    private String newPassword;

    public ForgotPasswordAggregate() {
    }

    @CommandHandler
    public ForgotPasswordAggregate(ForgotPasswordCommand forgotPasswordCommand) {

        var forgotPasswordEvent = new ForgotPasswordEvent();
        forgotPasswordEvent.setChangePasswordId(forgotPasswordCommand.getChangePasswordId());
        forgotPasswordEvent.setNewPassword(forgotPasswordCommand.getNewPassword());

        AggregateLifecycle.apply(forgotPasswordEvent);
    }

    @EventSourcingHandler
    public void on(ForgotPasswordEvent forgotPasswordEvent) {
        this.id = UUID.randomUUID().toString();
        this.changePasswordId = forgotPasswordEvent.getChangePasswordId();
        this.newPassword = forgotPasswordEvent.getNewPassword();
    }
}
