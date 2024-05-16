package com.aburakkontas.manga_auth.domain.primitives;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Result<T> {
    private final T value;
    private final boolean success;
    private final String errorMessage;
    private final int code;

    private Result(T value, boolean success, String errorMessage, int code) {
        this.value = value;
        this.success = success;
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, true, null, 200);
    }


    public static <T> Result<T> success(T value, int code) {
        return new Result<>(value, true, null, code);
    }

    public static <T> Result<T> failure(String errorMessage, int code) {
        return new Result<>(null, false, errorMessage, code);
    }

    public static <T> Result<T> successWithoutValue() {
        return success(null, 204);
    }
}
