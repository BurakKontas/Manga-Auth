package com.aburakkontas.manga_auth.application.aggregates;

import com.aburakkontas.manga_auth.application.commands.EmailVerificationCommand;
import com.aburakkontas.manga_auth.application.events.EmailVerifiedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class EmailVerificationAggregate {

    @AggregateIdentifier
    private String id;
    private String verificationId;
    private String oneTimeCode;

    public EmailVerificationAggregate() {
    }

    @CommandHandler
    public EmailVerificationAggregate(EmailVerificationCommand emailVerificationCommand) {
        emailVerificationCommand.throwIfInvalid();

        var emailVerifiedEvent = new EmailVerifiedEvent();
        emailVerifiedEvent.setVerificationId(emailVerificationCommand.getVerificationId());
        emailVerifiedEvent.setOneTimeCode(emailVerificationCommand.getOneTimeCode());

        AggregateLifecycle.apply(emailVerifiedEvent);
    }

    @EventSourcingHandler
    public void on(EmailVerifiedEvent emailVerifiedEvent) {
        this.id = UUID.randomUUID().toString();
        this.verificationId = emailVerifiedEvent.getVerificationId();
        this.oneTimeCode = emailVerifiedEvent.getOneTimeCode();
    }
}
