package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.domain.dtos.HasRoleDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga_axon.auth.queries.RoleCheckQuery;
import com.aburakkontas.manga_axon.auth.queries.results.RoleCheckQueryResult;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class RoleCheckQueryHandler {

    private final AuthRepository authRepository;

    public RoleCheckQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public RoleCheckQueryResult handle(RoleCheckQuery roleCheckQuery) {
        var email = roleCheckQuery.getEmail();
        var role = roleCheckQuery.getRole();

        var hasRoleDto = new HasRoleDTO(email, role);

        var result = authRepository.hasRole(hasRoleDto);

        var queryResult = new RoleCheckQueryResult();
        queryResult.setHasRole(result.isHasRole());

        return queryResult;
    }

}
