package com.aburakkontas.manga_auth.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FusionConfig {
    // Define fields as static
    public static String clientId;
    public static String clientSecret;
    public static String applicationId;
    public static String apiKey;
    public static String oauth2RedirectUri;
    public static String fusionUri;

    // Inject property values into static fields
    @Value("${fusionauth.clientid}")
    public void setClientId(String clientId) {
        FusionConfig.clientId = clientId;
    }

    @Value("${fusionauth.clientsecret}")
    public void setClientSecret(String clientSecret) {
        FusionConfig.clientSecret = clientSecret;
    }

    @Value("${fusionauth.applicationid}")
    public void setApplicationId(String applicationId) {
        FusionConfig.applicationId = applicationId;
    }

    @Value("${fusionauth.apikey}")
    public void setApiKey(String apiKey) {
        FusionConfig.apiKey = apiKey;
    }

    @Value("${fusionauth.oauth2.redirecturi}")
    public void setOauth2RedirectUri(String oauth2RedirectUri) {
        FusionConfig.oauth2RedirectUri = oauth2RedirectUri;
    }

    @Value("${fusionauth.uri}")
    public void setFusionUri(String fusionUri) {
        FusionConfig.fusionUri = fusionUri;
    }
}
