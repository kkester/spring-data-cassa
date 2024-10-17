package io.pivotal.cassa.entrepreneur.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurGenerator;
import io.pivotal.cassa.board.TokenType;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class EntrepreneurGenerator implements IEntrepreneurGenerator {

    private final ISquareRetriever squareRetriever;
    private final EntrepreneurRepository entrepreneurRepository;
    private final EntrepreneurConverter entrepreneurConverter;
    private final Faker faker;

    public Entrepreneur create(UUID monopolyId, TokenType tokenType, boolean human) {
        EntrepreneurEntity entrepreneurEntity = EntrepreneurEntity.builder()
            .id(Uuids.timeBased())
            .name(faker.funnyName().name())
            .tokenType(tokenType)
            .funds(1500)
            .monopolyId(monopolyId)
            .squareId(squareRetriever.getSquareDetailsByName("GO").getId())
            .human(human)
            .build();
        entrepreneurRepository.save(entrepreneurEntity);
        return entrepreneurConverter.toEntrepreneur(entrepreneurEntity);
    }

    public Entrepreneur createWithAnyTokenExcept(UUID monopolyId, TokenType...tokens) {
        List<TokenType> availableTokens = new ArrayList<>(Arrays.asList(TokenType.values()));
        availableTokens.removeAll(Arrays.asList(tokens));
        TokenType randomToken = availableTokens.get(faker.random().nextInt(availableTokens.size() - 1));
        return create(monopolyId, randomToken, false);
    }
}
