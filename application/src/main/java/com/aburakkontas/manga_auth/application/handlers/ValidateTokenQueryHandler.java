package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.domain.dtos.ValidateTokenDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga.common.auth.queries.ValidateTokenQuery;
import com.aburakkontas.manga.common.auth.queries.results.ValidateTokenQueryResult;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class ValidateTokenQueryHandler {

    private final AuthRepository authRepository;

    public ValidateTokenQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public ValidateTokenQueryResult handle(ValidateTokenQuery validateTokenQuery) {
        var token = validateTokenQuery.getToken();

        var validateTokenDto = new ValidateTokenDTO(token);

        var result = authRepository.validateToken(validateTokenDto);

        var queryResult = new ValidateTokenQueryResult();
        queryResult.setValid(result.isValid());

        return queryResult;
    }
}
