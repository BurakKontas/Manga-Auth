package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga.common.auth.queries.GetUserDetailsByIdQuery;
import com.aburakkontas.manga.common.auth.queries.results.GetUserDetailsByIdQueryResult;
import com.aburakkontas.manga_auth.domain.dtos.GetUserDetailsByIdDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GetUserDetailsByIdQueryHandler {

    private final AuthRepository authRepository;

    public GetUserDetailsByIdQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public GetUserDetailsByIdQueryResult handle(GetUserDetailsByIdQuery query) {
        var dto = new GetUserDetailsByIdDTO(query.getUserId());

        var user = authRepository.getUserDetailsById(dto);

        var queryResult = new GetUserDetailsByIdQueryResult();
        queryResult.setUserId(user.getUserId());
        queryResult.setEmail(user.getEmail());
        queryResult.setFirstName(user.getFirstName());
        queryResult.setLastName(user.getLastName());
        queryResult.setPermissions(user.getPermissions());
        queryResult.setLastLogin(user.getLastLogin());
        queryResult.setUsername(user.getUsername());

        return queryResult;
    }
}
