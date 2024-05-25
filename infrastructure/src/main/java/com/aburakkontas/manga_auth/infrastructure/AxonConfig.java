package com.aburakkontas.manga_auth.infrastructure;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGatewayFactory;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.ConfigurerModule;
import org.axonframework.messaging.interceptors.CorrelationDataInterceptor;
import org.axonframework.tracing.SpanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    // omitting other configuration methods...
    @Bean
    public CustomGateway customCommandGateway(CommandBus commandBus) {
        CommandGatewayFactory factory = CommandGatewayFactory.builder()
                .commandBus(commandBus)
                .build();
        return factory.createGateway(CustomGateway.class);
    }
}