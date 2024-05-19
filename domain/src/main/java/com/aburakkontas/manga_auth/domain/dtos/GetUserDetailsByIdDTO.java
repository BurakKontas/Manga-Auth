package com.aburakkontas.manga_auth.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetUserDetailsByIdDTO {
    private UUID userId;
}
