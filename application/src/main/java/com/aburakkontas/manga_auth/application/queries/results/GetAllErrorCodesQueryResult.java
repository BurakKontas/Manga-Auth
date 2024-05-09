package com.aburakkontas.manga_auth.application.queries.results;

import lombok.Data;

import java.util.Map;

@Data
public class GetAllErrorCodesQueryResult {
    private Map<String, String> errorCodes;
}
