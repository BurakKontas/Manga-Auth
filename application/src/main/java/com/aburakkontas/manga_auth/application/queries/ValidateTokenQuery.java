package com.aburakkontas.manga_auth.application.queries;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateTokenQuery {
    private String token;
}
