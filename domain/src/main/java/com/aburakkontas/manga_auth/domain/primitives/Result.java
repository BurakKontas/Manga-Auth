package com.aburakkontas.manga_auth.domain.primitives;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Result<T> {
    private final T value;
    private final boolean success;
    private final String errorMessage;
    private final String errorCode;

    private Result(T value, boolean success, String errorMessage, String errorCode) {
        this.value = value;
        this.success = success;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, true, null, null);
    }

    public static <T> Result<T> failure(String errorMessage, String errorCode) {
        return new Result<>(null, false, errorMessage, errorCode);
    }

    public static <T> Result<T> successWithoutValue() {
        return new Result<>(null, true, null, null);
    }
}
