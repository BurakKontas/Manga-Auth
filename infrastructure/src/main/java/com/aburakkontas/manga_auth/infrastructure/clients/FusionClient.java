package com.aburakkontas.manga_auth.infrastructure.clients;


import com.aburakkontas.manga_auth.infrastructure.configs.FusionConfig;
import io.fusionauth.client.FusionAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FusionClient {

    @Autowired
    private FusionConfig fusionConfig;

    private FusionAuthClient client;

    public FusionAuthClient getClient() {
        if (client == null) {
            client = new FusionAuthClient(FusionConfig.apiKey, FusionConfig.fusionUri);
        }
        return client;
    }
}
