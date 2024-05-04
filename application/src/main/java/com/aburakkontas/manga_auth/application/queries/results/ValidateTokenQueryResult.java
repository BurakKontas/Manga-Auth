package com.aburakkontas.manga_auth.application.queries.results;

import lombok.Data;

@Data
public class ValidateTokenQueryResult {
    private boolean isValid;
}
