package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga.common.auth.queries.GetUserDetailsFromTokenQuery;
import com.aburakkontas.manga.common.auth.queries.results.GetUserDetailsFromTokenQueryResult;
import com.aburakkontas.manga_auth.domain.dtos.GetUserDetailsFromTokenDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GetUserDetailsFromTokenQueryHandler {

    private final AuthRepository authRepository;

    public GetUserDetailsFromTokenQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public GetUserDetailsFromTokenQueryResult handle(GetUserDetailsFromTokenQuery getUserDetailsFromTokenDTO) {

        var dto = new GetUserDetailsFromTokenDTO(getUserDetailsFromTokenDTO.getToken());

        var userDetails = authRepository.getUserDetailsFromToken(dto);

        var queryResult = new GetUserDetailsFromTokenQueryResult();
        queryResult.setEmail(userDetails.getEmail());
        queryResult.setFirstName(userDetails.getFirstName());
        queryResult.setLastName(userDetails.getLastName());
        queryResult.setPermissions(userDetails.getPermissions());
        queryResult.setUsername(userDetails.getUsername());
        queryResult.setUserId(userDetails.getUserId());
        queryResult.setLastLogin(userDetails.getLastLogin());

        return queryResult;
    }
}
