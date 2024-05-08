package com.aburakkontas.manga_auth.application;

import com.aburakkontas.manga_auth.application.interceptors.LoggingCommandHandlerInterceptor;
import com.aburakkontas.manga_auth.application.interceptors.LoggingQueryHandlerInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.monitoring.NoOpMessageMonitor;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.queryhandling.SimpleQueryBus;
import org.axonframework.tracing.SpanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonInterceptorRegistrations {
    @Bean
    public CommandBus commandBus(TransactionManager transactionManager) {
        SimpleCommandBus commandBus = SimpleCommandBus.builder()
                .transactionManager(transactionManager)
                .messageMonitor(NoOpMessageMonitor.INSTANCE)
                .build();

        commandBus.registerHandlerInterceptor(new LoggingCommandHandlerInterceptor());

        return commandBus;
    }

    @Bean
    public QueryBus queryBus(SpanFactory spanFactory,
                             TransactionManager transactionManager,
                             QueryUpdateEmitter updateEmitter) {
        var queryBus = SimpleQueryBus.builder()
                .transactionManager(transactionManager)
                .queryUpdateEmitter(updateEmitter)
                .messageMonitor(NoOpMessageMonitor.INSTANCE)
                .spanFactory(spanFactory)
                .build();

        queryBus.registerHandlerInterceptor(new LoggingQueryHandlerInterceptor());

        return queryBus;
    }
}
