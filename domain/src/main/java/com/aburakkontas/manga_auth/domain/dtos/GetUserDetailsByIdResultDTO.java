package com.aburakkontas.manga_auth.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GetUserDetailsByIdResultDTO {
    private String email;
    private String firstName;
    private String lastName;
    private ArrayList<String> permissions;
    private String username;
    private UUID userId;
    private ZonedDateTime lastLogin;
}
