package io.pivotal.cassa.monopoly.components;

import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurRetriever;
import io.pivotal.cassa.monopoly.Monopoly;
import io.pivotal.cassa.monopoly.db.MonopolyEntity;
import io.pivotal.cassa.monopoly.db.MonopolyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MonopolyRetriever {

    private final MonopolyRepository monopolyRepository;
    private final IEntrepreneurRetriever entrepreneurRetriever;
    private final ISquareRetriever squareRetriever;

    public Monopoly get(UUID monopolyId) {
        return monopolyRepository.findById(monopolyId)
            .map(this::convert)
            .orElseThrow(RuntimeException::new);
    }

    private Monopoly convert(MonopolyEntity monopolyEntity) {
        List<Entrepreneur> entrepreneurs = entrepreneurRetriever.retrieveEntrepreneurs(monopolyEntity.getId());
        List<Square> squares = squareRetriever.retrieveSquares(monopolyEntity.getId());
        return Monopoly.builder()
            .id(monopolyEntity.getId())
            .pot(monopolyEntity.getPot())
            .players(entrepreneurs)
            .gameOver(monopolyEntity.isGameOver())
            .squares(squares)
            .build();
    }
}
