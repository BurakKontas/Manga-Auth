package com.aburakkontas.manga_auth.application.handlers;


import com.aburakkontas.manga_auth.domain.dtos.LoginDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga_axon.auth.queries.LoginQuery;
import com.aburakkontas.manga_axon.auth.queries.results.LoginQueryResult;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LoginQueryHandler {

    private final AuthRepository authRepository;

    public LoginQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public LoginQueryResult handle(LoginQuery loginQuery) {

        var email = loginQuery.getEmail();
        var password = loginQuery.getPassword();

        var loginDto = new LoginDTO(email, password);

        var loginResultDto = authRepository.login(loginDto);

        var queryResult = new LoginQueryResult();
        queryResult.setRefreshToken(loginResultDto.getRefreshToken());
        queryResult.setToken(loginResultDto.getToken());

        return queryResult;
    }

}
