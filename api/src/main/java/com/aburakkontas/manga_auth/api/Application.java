package com.aburakkontas.manga_auth.api;

import com.aburakkontas.manga_auth.application.ApplicationInjection;
import com.aburakkontas.manga_auth.infrastructure.InfrastructureInjection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import({ApplicationInjection.class, InfrastructureInjection.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
