package com.aburakkontas.manga_auth.domain.exceptions;

import lombok.Getter;

@Getter
public class ExceptionWithErrorCode extends RuntimeException {
    private final int code;

    public ExceptionWithErrorCode(String message, int code) {
        super(message);
        this.code = code;
    }
}