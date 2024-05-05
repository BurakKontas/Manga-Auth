package com.aburakkontas.manga_auth.api.controllers;

import com.aburakkontas.manga_auth.application.queries.OAuth2CallbackQuery;
import com.aburakkontas.manga_auth.application.queries.results.OAuth2CallbackQueryResult;
import com.aburakkontas.manga_auth.contracts.response.OAuth2CallbackResponse;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/oauth2/callback")
public class OAuth2Controller {

    private final QueryGateway queryGateway;

    @Autowired
    public OAuth2Controller(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping()
    public ResponseEntity<OAuth2CallbackResponse> handleCallback(@RequestParam("code") String code) {

        var query = OAuth2CallbackQuery.builder()
                .code(code)
                .build();

        var result = queryGateway.query(query, OAuth2CallbackQueryResult.class).join();

        var response = new OAuth2CallbackResponse();
        response.setToken(result.getToken());
        response.setRefreshToken(result.getRefreshToken());

        return ResponseEntity.ok(response);
    }
}
