package com.aburakkontas.manga_auth.application.queries;

import an.awesome.pipelinr.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenQuery {
    private String refreshToken;
}
