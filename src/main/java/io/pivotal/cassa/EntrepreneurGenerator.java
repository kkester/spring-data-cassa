package io.pivotal.cassa;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EntrepreneurGenerator {

    private final EntrepreneurRepository entrepreneurRepository;
    private final Faker faker;

    public Entrepreneur create(TokenType tokenType) {
        Entrepreneur entrepreneur = Entrepreneur.builder()
            .id(UUID.randomUUID())
            .name(faker.funnyName().name())
            .tokenType(tokenType)
            .funds(1500.0)
            .build();
        return entrepreneurRepository.save(entrepreneur);
    }
}
