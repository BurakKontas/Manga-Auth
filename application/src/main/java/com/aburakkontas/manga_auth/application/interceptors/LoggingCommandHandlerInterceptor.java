package com.aburakkontas.manga_auth.application.interceptors;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.ResultMessage;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

public class LoggingCommandHandlerInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {
    private static final Logger logger = LoggerFactory.getLogger(LoggingCommandHandlerInterceptor.class);


    @Override
    public Object handle(@Nonnull UnitOfWork<? extends CommandMessage<?>> unitOfWork, @Nonnull InterceptorChain interceptorChain) throws Exception {
        CommandMessage<?> commandMessage = unitOfWork.getMessage();
        logger.info("Command received: {}", commandMessage.getPayloadType().getName());
        try {
            Object result = interceptorChain.proceed();
            if (result instanceof CommandResultMessage<?> commandResultMessage) {
                if (commandResultMessage.isExceptional()) {
                    logger.error("Command failed: {}", commandMessage.getPayloadType().getName());
                } else {
                    logger.info("Command successful: {}", commandMessage.getPayloadType().getName());
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("Command failed: {}", commandMessage.getPayloadType().getName());
            throw e;
        }
    }
}
