package com.aburakkontas.manga_auth.domain.exceptions;

import lombok.Getter;

@Getter
public class ExceptionWithErrorCode extends RuntimeException {
    private final String code;

    public ExceptionWithErrorCode(String message, String code) {
        super(message);
        this.code = code;
    }
}