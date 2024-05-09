package com.aburakkontas.manga_auth.contracts.response;

import lombok.Data;

import java.util.Map;

@Data
public class GetAllErrorCodesResponse {
    private Map<String, String> errorCodes;
}
