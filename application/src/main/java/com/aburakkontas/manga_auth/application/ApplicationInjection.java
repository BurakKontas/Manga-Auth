package com.aburakkontas.manga_auth.application;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;

import java.util.stream.Stream;

import com.aburakkontas.manga_auth.application.handlers.ChangePasswordCommandHandler;
import com.aburakkontas.manga_auth.application.handlers.Pong;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ChangePasswordCommandHandler.class})
public class ApplicationInjection {

    @Bean
    public Pipeline pipeline(ApplicationContext context) {
        Pipelinr pipelinr = new Pipelinr();

        // Add all command handlers
        context.getBeansOfType(Command.Handler.class).values().forEach(handler ->
                pipelinr.with(() -> Stream.of(handler)));

        // Add all notification handlers
        context.getBeansOfType(Notification.Handler.class).values().forEach(handler ->
                pipelinr.with(() -> Stream.of(handler)));

        return pipelinr;
    }
}
