package com.aburakkontas.manga_auth.domain.entities.registration.valueobjects;

import com.aburakkontas.manga_auth.domain.primitives.ValueObject;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public class Email extends ValueObject<String> {
    private final String value;

    public Email(String value) {
        this.value = value;
    }

    @Override
    public Stream<String> getAtomicValues() {
        return value.lines();
    }
}
