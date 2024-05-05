package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.application.queries.OAuth2CallbackQuery;
import com.aburakkontas.manga_auth.application.queries.results.OAuth2CallbackQueryResult;
import com.aburakkontas.manga_auth.domain.dtos.ExchangeOAuth2CodeForJWTDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2CallbackQueryHandler {

    private final AuthRepository authRepository;

    public OAuth2CallbackQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public OAuth2CallbackQueryResult handle(OAuth2CallbackQuery oAuth2CallbackQuery) {

        var exchangeOAuth2CodeForJWTDTO = new ExchangeOAuth2CodeForJWTDTO(oAuth2CallbackQuery.getCode());

        var token = authRepository.exchangeOAuth2CodeForJWT(exchangeOAuth2CodeForJWTDTO);

        var queryResult = new OAuth2CallbackQueryResult();
        queryResult.setToken(token.getToken());
        queryResult.setRefreshToken(token.getRefreshToken());

        return queryResult;
    }
}
