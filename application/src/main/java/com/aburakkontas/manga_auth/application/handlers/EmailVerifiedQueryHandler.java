package com.aburakkontas.manga_auth.application.handlers;

import com.aburakkontas.manga_auth.domain.dtos.VerifyRegistrationWithCodeDTO;
import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga.common.auth.queries.EmailVerificationQuery;
import com.aburakkontas.manga.common.auth.queries.results.EmailVerificationQueryResult;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class EmailVerifiedQueryHandler {

    private final AuthRepository authRepository;

    public EmailVerifiedQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    @QueryHandler
    public EmailVerificationQueryResult handle(EmailVerificationQuery emailVerificationQuery) {
        var verificationId = emailVerificationQuery.getVerificationId();
        var oneTimeCode = emailVerificationQuery.getOneTimeCode();

        var verifyRegistrationWithCodeDto = new VerifyRegistrationWithCodeDTO(verificationId, oneTimeCode);

        var result = authRepository.verifyRegistrationWithCode(verifyRegistrationWithCodeDto);

        var queryResult = new EmailVerificationQueryResult();
        queryResult.setVerified(result.isVerified());

        return queryResult;
    }

}
