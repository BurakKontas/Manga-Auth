package com.aburakkontas.manga_auth.application.aggregates;

import com.aburakkontas.manga_auth.application.commands.ChangePasswordCommand;
import com.aburakkontas.manga_auth.application.events.ChangePasswordEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class ChangePasswordAggregate {

    @AggregateIdentifier
    private String id;
    private String email;
    private String currentPassword;
    private String newPassword;

    public ChangePasswordAggregate() {
    }

    @CommandHandler
    public ChangePasswordAggregate(ChangePasswordCommand changePasswordCommand) {
        changePasswordCommand.throwIfInvalid();

        var changePasswordEvent = new ChangePasswordEvent();
        changePasswordEvent.setEmail(changePasswordCommand.getEmail());
        changePasswordEvent.setCurrentPassword(changePasswordCommand.getCurrentPassword());
        changePasswordEvent.setNewPassword(changePasswordCommand.getNewPassword());

        AggregateLifecycle.apply(changePasswordEvent);
    }

    @EventSourcingHandler
    public void on(ChangePasswordEvent changePasswordEvent) {
        this.id = UUID.randomUUID().toString();
        this.email = changePasswordEvent.getEmail();
        this.currentPassword = changePasswordEvent.getCurrentPassword();
        this.newPassword = changePasswordEvent.getNewPassword();
    }
}
