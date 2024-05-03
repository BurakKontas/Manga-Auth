package com.aburakkontas.manga_auth.api;

import com.aburakkontas.manga_auth.application.ApplicationInjection;
import com.aburakkontas.manga_auth.infrastructure.InfrastructureInjection;
import com.aburakkontas.manga_auth.infrastructure.configs.FusionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApplicationInjection.class, InfrastructureInjection.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
