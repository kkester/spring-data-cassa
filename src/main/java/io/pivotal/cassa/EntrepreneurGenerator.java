package io.pivotal.cassa;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntrepreneurGenerator {

    private final Faker faker;

    public Entrepreneur create(TokenType tokenType) {
        return Entrepreneur.builder()
            .name(faker.funnyName().name())
            .token(tokenType)
            .funds(1500.0)
            .build();
    }
}
