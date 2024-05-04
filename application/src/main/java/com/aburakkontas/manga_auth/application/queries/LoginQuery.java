package com.aburakkontas.manga_auth.application.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginQuery {
    private String email;
    private String password;
}
