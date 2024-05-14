package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_axon.auth.queries.ChangePasswordQuery;
import com.aburakkontas.manga_axon.auth.queries.results.ChangePasswordQueryResult;
import com.aburakkontas.manga_auth.domain.dtos.PasswordChangeDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordQueryHandler {

    private final AuthRepository authRepository;

    public ChangePasswordQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public ChangePasswordQueryResult handle(ChangePasswordQuery password) {
        var email = password.getEmail();
        var currentPassword = password.getCurrentPassword();
        var newPassword = password.getNewPassword();

        var passwordChangeDTO = new PasswordChangeDTO(email, currentPassword, newPassword);

        var result = authRepository.changePassword(passwordChangeDTO);

        var queryResult = new ChangePasswordQueryResult();
        queryResult.setChanged(result.isSuccess());

        return queryResult;
    }

}
