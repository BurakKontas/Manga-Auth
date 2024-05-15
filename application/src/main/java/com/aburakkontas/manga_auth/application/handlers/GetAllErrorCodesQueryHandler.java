package com.aburakkontas.manga_auth.application.handlers;



import com.aburakkontas.manga_auth.domain.repositories.AuthRepository;
import com.aburakkontas.manga.common.auth.queries.GetAllErrorCodesQuery;
import com.aburakkontas.manga.common.auth.queries.results.GetAllErrorCodesQueryResult;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GetAllErrorCodesQueryHandler {

    private final AuthRepository authRepository;

    public GetAllErrorCodesQueryHandler(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @QueryHandler
    public GetAllErrorCodesQueryResult handle(GetAllErrorCodesQuery getAllErrorCodesQuery) {
        var errorCodes = authRepository.getAllErrorCodes();

        var queryResult = new GetAllErrorCodesQueryResult();
        queryResult.setErrorCodes(errorCodes.getErrorCodes());

        return queryResult;
    }
}
