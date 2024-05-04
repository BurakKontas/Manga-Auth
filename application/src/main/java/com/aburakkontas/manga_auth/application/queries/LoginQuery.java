package com.aburakkontas.manga_auth.application.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginQuery {
    private String email;
    private String password;
}
