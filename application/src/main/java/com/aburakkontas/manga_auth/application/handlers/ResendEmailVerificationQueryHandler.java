package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga.common.auth.queries.ResendEmailVerificationQuery;

import com.aburakkontas.manga_auth.domain.dtos.ResendEmailVerificationDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga.common.auth.queries.results.ResendEmailVerificationQueryResult;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class ResendEmailVerificationQueryHandler {

    private final AuthRepository authRepository;

    public ResendEmailVerificationQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public ResendEmailVerificationQueryResult handle(ResendEmailVerificationQuery resendEmailVerificationQuery) {
        var email = resendEmailVerificationQuery.getEmail();

        var resendEmailVerificationDto = new ResendEmailVerificationDTO(email);

        var result = authRepository.resendEmailVerification(resendEmailVerificationDto);

        var queryResult = new ResendEmailVerificationQueryResult();
        queryResult.setRegistrationId(result.getRegistrationVerificationId());

        return queryResult;
    }
}
