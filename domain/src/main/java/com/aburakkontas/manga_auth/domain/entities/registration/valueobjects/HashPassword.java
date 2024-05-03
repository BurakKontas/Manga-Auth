package com.aburakkontas.manga_auth.domain.entities.registration.valueobjects;

import com.aburakkontas.manga_auth.domain.primitives.ValueObject;

import java.util.stream.Stream;

public class HashPassword extends ValueObject<String> {
    private final String value;

    public HashPassword(String value) {
        this.value = value;
    }

    @Override
    public Stream<String> getAtomicValues() {
        return Stream.empty();
    }
}