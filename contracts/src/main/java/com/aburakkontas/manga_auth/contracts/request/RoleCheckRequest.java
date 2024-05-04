package com.aburakkontas.manga_auth.contracts.request;

import lombok.Data;

@Data
public class RoleCheckRequest {
    private String email;
    private String role;
}
