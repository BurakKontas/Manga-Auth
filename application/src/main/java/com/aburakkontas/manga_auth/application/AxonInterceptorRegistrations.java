package com.aburakkontas.manga_auth.application;

import com.aburakkontas.manga_auth.application.interceptors.ExceptionQueryHandlerInterceptor;
import com.aburakkontas.manga_auth.application.interceptors.LoggingCommandHandlerInterceptor;
import com.aburakkontas.manga_auth.application.interceptors.LoggingQueryHandlerInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.monitoring.MessageMonitor;
import org.axonframework.queryhandling.*;
import org.axonframework.springboot.TracingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonInterceptorRegistrations {
    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = SimpleCommandBus.builder()
                .build();
        commandBus.registerHandlerInterceptor(new LoggingCommandHandlerInterceptor());

        return commandBus;
    }

    @Bean
    public QueryGateway queryGateway(QueryBus queryBus) {
        queryBus.registerHandlerInterceptor(new LoggingQueryHandlerInterceptor());
        queryBus.registerHandlerInterceptor(new ExceptionQueryHandlerInterceptor());

        QueryGateway queryGateway = DefaultQueryGateway.builder()
                .queryBus(queryBus)
                .build();
        return queryGateway;
    }

}
