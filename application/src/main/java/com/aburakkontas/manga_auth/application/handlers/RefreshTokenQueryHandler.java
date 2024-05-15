package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga.common.auth.queries.RefreshTokenQuery;
import com.aburakkontas.manga.common.auth.queries.results.RefreshTokenQueryResult;
import com.aburakkontas.manga_auth.domain.dtos.RefreshTokenDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenQueryHandler {

    private final AuthRepository authRepository;

    public RefreshTokenQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public RefreshTokenQueryResult handle(RefreshTokenQuery refreshTokenQuery) {
        var refreshToken = refreshTokenQuery.getRefreshToken();

        var refreshTokenDto = new RefreshTokenDTO(refreshToken);

        var result = authRepository.refreshToken(refreshTokenDto);

        var queryResult = new RefreshTokenQueryResult();
        queryResult.setToken(result.getToken());
        queryResult.setRefreshToken(result.getRefreshToken());

        return queryResult;
    }
}
