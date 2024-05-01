package com.aburakkontas.manga_auth.application;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;

import java.util.stream.Stream;

import com.aburakkontas.manga_auth.application.handlers.Pong;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjection {

    @Bean
    public Pipeline pipeline() {
        return new Pipelinr()
                .with(() -> Stream.of(new Pong()));
    }
}
