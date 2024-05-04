package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.application.queries.RegisterQuery;
import com.aburakkontas.manga_auth.application.queries.results.LoginQueryResult;
import com.aburakkontas.manga_auth.application.queries.results.RegisterQueryResult;
import com.aburakkontas.manga_auth.domain.dtos.RegisterDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RegisterQueryHandler {

    private final AuthRepository authRepository;

    public RegisterQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public RegisterQueryResult handle(RegisterQuery registerQuery) {

        var email = registerQuery.getEmail();
        var firstName = registerQuery.getFirstName();
        var lastName = registerQuery.getLastName();
        var password = registerQuery.getPassword();

        var registerDto = new RegisterDTO(email, firstName, lastName, password);
        var registerResultDto = authRepository.register(registerDto);

        var queryResult = new RegisterQueryResult();
        queryResult.setRegistrationId(registerResultDto.getRegistrationId());


        return queryResult;
    }

}
