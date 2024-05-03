package com.aburakkontas.manga_auth.infrastructure;

import com.aburakkontas.manga_auth.infrastructure.clients.FusionClient;
import com.aburakkontas.manga_auth.infrastructure.configs.FusionConfig;
import com.aburakkontas.manga_auth.infrastructure.repositories.AuthRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AuthRepositoryImpl.class, FusionClient.class, FusionConfig.class, AxonXstreamConfig.class})
public class InfrastructureInjection {

}
