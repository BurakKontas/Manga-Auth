package com.aburakkontas.manga_auth.application.queries;

import com.aburakkontas.manga_auth.application.validators.Validatable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class RegisterQuery extends Validatable {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private final String email;

    @NotBlank(message = "First name cannot be blank")
    private final String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private final String lastName;

    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain at least one uppercase letter"),
            @Pattern(regexp = "(?=.*\\d).+", message = "Password must contain at least one digit"),
            @Pattern(regexp = "(?=.*\\W).+", message = "Password must contain at least one special character")
    })
    private final String password;
}
