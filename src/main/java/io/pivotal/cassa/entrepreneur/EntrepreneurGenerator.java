package io.pivotal.cassa.entrepreneur;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntrepreneurGenerator {

    private final EntrepreneurRepository entrepreneurRepository;
    private final Faker faker;

    public Entrepreneur create(TokenType tokenType) {
        Entrepreneur entrepreneur = Entrepreneur.builder()
            .id(Uuids.timeBased())
            .name(faker.funnyName().name())
            .tokenType(tokenType)
            .funds(1500.0)
            .build();
        return entrepreneurRepository.save(entrepreneur);
    }
}
