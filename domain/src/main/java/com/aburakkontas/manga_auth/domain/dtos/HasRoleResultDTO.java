package com.aburakkontas.manga_auth.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HasRoleResultDTO {
    public boolean hasRole;
    public String email;
}
