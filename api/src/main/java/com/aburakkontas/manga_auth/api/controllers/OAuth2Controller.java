package com.aburakkontas.manga_auth.api.controllers;

import com.aburakkontas.manga_auth.contracts.response.OAuth2CallbackResponse;
import com.aburakkontas.manga_axon.auth.queries.OAuth2CallbackQuery;
import com.aburakkontas.manga_axon.auth.queries.results.OAuth2CallbackQueryResult;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/oauth2/callback")
public class OAuth2Controller {

    private final QueryGateway queryGateway;
    private final Environment env;

    @Autowired
    public OAuth2Controller(QueryGateway queryGateway, Environment env) {
        this.queryGateway = queryGateway;
        this.env = env;
    }

    @GetMapping()
    public ResponseEntity<Void> handleCallback(@RequestParam("code") String code) {

        var query = OAuth2CallbackQuery.builder()
                .code(code)
                .build();

        var result = queryGateway.query(query, OAuth2CallbackQueryResult.class).join();

        var response = new OAuth2CallbackResponse();
        response.setToken(result.getToken());
        response.setRefreshToken(result.getRefreshToken());

        var redirectUri = env.getProperty("frontend.uri");
        var uri = redirectUri + "/login-with-token?token=" + response.getToken() + "&refreshToken=" + response.getRefreshToken() + "&status=success";
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(uri))
                .build();
    }
}
