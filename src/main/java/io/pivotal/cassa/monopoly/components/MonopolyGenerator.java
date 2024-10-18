package io.pivotal.cassa.monopoly.components;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurGenerator;
import io.pivotal.cassa.board.TokenType;
import io.pivotal.cassa.monopoly.web.MonopolyDto;
import io.pivotal.cassa.monopoly.db.MonopolyEntity;
import io.pivotal.cassa.monopoly.db.MonopolyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MonopolyGenerator {

    private final IEntrepreneurGenerator entrepreneurGenerator;
    private final MonopolyRepository monopolyRepository;

    public MonopolyDto create(@RequestParam TokenType token) {
        MonopolyEntity monopolyEntity = MonopolyEntity.builder()
            .id(Uuids.timeBased())
            .pot(200)
            .gameOver(false)
            .build();
        monopolyRepository.save(monopolyEntity);

        Entrepreneur entrepreneur1 = entrepreneurGenerator.create(monopolyEntity.getId(), token, true);
        Entrepreneur entrepreneur2 = entrepreneurGenerator.createWithAnyTokenExcept(monopolyEntity.getId(), token);
        Entrepreneur entrepreneur3 = entrepreneurGenerator.createWithAnyTokenExcept(monopolyEntity.getId(), token, entrepreneur2.getTokenType());
        return MonopolyDto.builder()
            .id(monopolyEntity.getId())
            .pot(monopolyEntity.getPot())
            .players(List.of(entrepreneur1, entrepreneur2, entrepreneur3))
            .build();
    }
}
