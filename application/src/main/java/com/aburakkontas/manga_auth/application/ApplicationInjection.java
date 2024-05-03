package com.aburakkontas.manga_auth.application;

import com.aburakkontas.manga_auth.application.aggregates.RegistrationAggregate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RegistrationAggregate.class})
public class ApplicationInjection {
}
