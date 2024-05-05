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
    public static String GoogleClientId;
    public static String GoogleScope;
    public static String GoogleBaseUri;
    public static String GoogleResponseType;
    public static String GoogleAccessType;
    public static String GooglePrompt;
    public static String GoogleFlowName;
    public static String GoogleService;
    public static String GoogleDDM;
    public static String GoogleO2V;

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

    @Value("${fusionauth.oauth2.google.clientid}")
    public void setGoogleClientId(String GoogleClientId) {
        FusionConfig.GoogleClientId = GoogleClientId;
    }

    @Value("${fusionauth.oauth2.google.scope}")
    public void setGoogleScope(String GoogleScope) {
        FusionConfig.GoogleScope = GoogleScope;
    }

    @Value("${fusionauth.oauth2.google.baseuri}")
    public void setGoogleBaseUri(String GoogleBaseUri) {
        FusionConfig.GoogleBaseUri = GoogleBaseUri;
    }

    @Value("${fusionauth.oauth2.google.responsetype}")
    public void setGoogleResponseType(String GoogleResponseType) {
        FusionConfig.GoogleResponseType = GoogleResponseType;
    }

    @Value("${fusionauth.oauth2.google.accesstype}")
    public void setGoogleAccessType(String GoogleAccessType) {
        FusionConfig.GoogleAccessType = GoogleAccessType;
    }

    @Value("${fusionauth.oauth2.google.prompt}")
    public void setGooglePrompt(String GooglePrompt) {
        FusionConfig.GooglePrompt = GooglePrompt;
    }

    @Value("${fusionauth.oauth2.google.flowname}")
    public void setGoogleFlowName(String GoogleFlowName) {
        FusionConfig.GoogleFlowName = GoogleFlowName;
    }

    @Value("${fusionauth.oauth2.google.service}")
    public void setGoogleService(String GoogleService) {
        FusionConfig.GoogleService = GoogleService;
    }

    @Value("${fusionauth.oauth2.google.ddm}")
    public void setGoogleDDM(String GoogleDDM) {
        FusionConfig.GoogleDDM = GoogleDDM;
    }

    @Value("${fusionauth.oauth2.google.o2v}")
    public void setGoogleO2V(String GoogleO2V) {
        FusionConfig.GoogleO2V = GoogleO2V;
    }
}
