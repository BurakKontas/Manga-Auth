package com.aburakkontas.manga_auth.application.aggregates;

import com.aburakkontas.manga_auth.application.commands.RegisterCommand;
import com.aburakkontas.manga_auth.application.events.RegistrationCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aggregate
public class RegistrationAggregate {

    @AggregateIdentifier
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public RegistrationAggregate() {
    }

    @CommandHandler
    public RegistrationAggregate(RegisterCommand registerCommand)  {
        registerCommand.throwIfInvalid();

        var registrationCreatedEvent = new RegistrationCreatedEvent(registerCommand.getEmail(), registerCommand.getFirstName(), registerCommand.getLastName(), registerCommand.getPassword());
//        BeanUtils.copyProperties(registerCommand, registrationCreatedEvent);

        AggregateLifecycle.apply(registrationCreatedEvent);

    }

    @EventSourcingHandler
    public void on(RegistrationCreatedEvent registrationCreatedEvent) {
        this.id = UUID.randomUUID().toString();
        this.email = registrationCreatedEvent.getEmail();
        this.firstName = registrationCreatedEvent.getFirstName();
        this.lastName = registrationCreatedEvent.getLastName();
        this.password = registrationCreatedEvent.getPassword();
    }


}
