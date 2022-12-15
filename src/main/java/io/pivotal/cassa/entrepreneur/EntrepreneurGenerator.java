package io.pivotal.cassa.entrepreneur;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.pivotal.cassa.board.SquareConstants;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class EntrepreneurGenerator {

    private final EntrepreneurRepository entrepreneurRepository;
    private final Faker faker;

    public Entrepreneur create(TokenType tokenType) {
        EntrepreneurEntity entrepreneurEntity = EntrepreneurEntity.builder()
            .id(Uuids.timeBased())
            .name(faker.funnyName().name())
            .tokenType(tokenType)
            .funds(1500.0)
            .squareName(SquareConstants.GO.getName())
            .build();
        entrepreneurRepository.save(entrepreneurEntity);
        return Entrepreneur.builder()
            .name(entrepreneurEntity.getName())
            .tokenType(entrepreneurEntity.getTokenType())
            .funds(entrepreneurEntity.getFunds())
            .square(entrepreneurEntity.getSquareName())
            .build();
    }

    public Entrepreneur createWithAnyTokenExcept(TokenType...tokens) {
        List<TokenType> availableTokens = new ArrayList<>(Arrays.asList(TokenType.values()));
        availableTokens.removeAll(Arrays.asList(tokens));
        TokenType randomToken = availableTokens.get(faker.random().nextInt(availableTokens.size() - 1));
        return create(randomToken);
    }
}
