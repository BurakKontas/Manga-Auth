package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga.common.auth.queries.GenerateGoogleLoginUriQuery;
import com.aburakkontas.manga.common.auth.queries.results.GenerateGoogleLoginUriQueryResult;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GenerateGoogleLoginUriQueryHandler {

    private final AuthRepository authRepository;

    public GenerateGoogleLoginUriQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public GenerateGoogleLoginUriQueryResult handle(GenerateGoogleLoginUriQuery generateGoogleLoginUriQuery) {
        var uri = authRepository.generateGoogleUri();

        var queryResult = new GenerateGoogleLoginUriQueryResult();
        queryResult.setUri(uri.getUri());

        return queryResult;
    }

}