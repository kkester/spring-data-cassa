package io.pivotal.cassa;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.EntrepreneurGenerator;
import io.pivotal.cassa.entrepreneur.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MonopolyGenerator {
    private final EntrepreneurGenerator entrepreneurGenerator;
    private final MonopolyRepository monopolyRepository;

    public Monopoly create(@RequestParam TokenType token) {
        MonopolyEntity monopolyEntity = MonopolyEntity.builder()
            .id(Uuids.timeBased())
            .pot(200)
            .build();
        monopolyRepository.save(monopolyEntity);

        Entrepreneur entrepreneur1 = entrepreneurGenerator.create(monopolyEntity.getId(), token, true);
        Entrepreneur entrepreneur2 = entrepreneurGenerator.createWithAnyTokenExcept(monopolyEntity.getId(), token);
        Entrepreneur entrepreneur3 = entrepreneurGenerator.createWithAnyTokenExcept(monopolyEntity.getId(), token, entrepreneur2.getTokenType());
        return Monopoly.builder()
            .id(monopolyEntity.getId())
            .pot(monopolyEntity.getPot())
            .players(List.of(entrepreneur1, entrepreneur2, entrepreneur3))
            .build();
    }
}
