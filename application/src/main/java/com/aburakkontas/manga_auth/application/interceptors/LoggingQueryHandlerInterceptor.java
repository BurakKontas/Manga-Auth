package com.aburakkontas.manga_auth.application.interceptors;

import com.aburakkontas.manga_auth.domain.primitives.Result;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.ResultMessage;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.queryhandling.QueryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

public class LoggingQueryHandlerInterceptor implements MessageHandlerInterceptor<QueryMessage<?,?>> {
    private static final Logger logger = LoggerFactory.getLogger(LoggingCommandHandlerInterceptor.class);


    @Override
    public Object handle(@Nonnull UnitOfWork<? extends QueryMessage<?,?>> unitOfWork, @Nonnull InterceptorChain interceptorChain) throws Exception {
        QueryMessage<?,?> queryMessage = unitOfWork.getMessage();
        logger.info("Query received: {}", queryMessage.getPayloadType().getName());
        try {
            Object result = interceptorChain.proceed();
            logger.info("Query successful: {}", queryMessage.getPayloadType().getName());
            return result;
        } catch (Exception e) {
            logger.error("Query failed: {}", queryMessage.getPayloadType().getName());
            throw e;
        }
    }
}
