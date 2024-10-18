package io.pivotal.cassa.monopoly.components;

import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.board.TokenType;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurRetriever;
import io.pivotal.cassa.monopoly.db.MonopolyEntity;
import io.pivotal.cassa.monopoly.db.MonopolyRepository;
import io.pivotal.cassa.monopoly.web.MonopolyDto;
import io.pivotal.cassa.monopoly.web.SquareDto;
import io.pivotal.cassa.property.IPropertyRetriever;
import io.pivotal.cassa.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MonopolyRetriever {

    private final MonopolyRepository monopolyRepository;
    private final IEntrepreneurRetriever entrepreneurRetriever;
    private final ISquareRetriever squareRetriever;
    private final IPropertyRetriever propertyRetriever;

    public MonopolyDto get(UUID monopolyId) {
        return monopolyRepository.findById(monopolyId)
            .map(this::convert)
            .orElseThrow(RuntimeException::new);
    }

    private MonopolyDto convert(MonopolyEntity monopolyEntity) {
        List<Entrepreneur> entrepreneurs = entrepreneurRetriever.retrieveEntrepreneurs(monopolyEntity.getId());
        List<SquareDto> squares = retrieveSquares(monopolyEntity.getId());
        return MonopolyDto.builder()
            .id(monopolyEntity.getId())
            .pot(monopolyEntity.getPot())
            .players(entrepreneurs)
            .gameOver(monopolyEntity.isGameOver())
            .squares(squares)
            .build();
    }

    private List<SquareDto> retrieveSquares(UUID monopolyId) {
        List<Entrepreneur> players = entrepreneurRetriever.retrieveEntrepreneurs(monopolyId);
        return squareRetriever.getAllSquares().stream()
                .map(square -> getSquareFor(monopolyId, square, players))
                .toList();
    }

    private SquareDto getSquareFor(UUID monopolyId, Square square, List<Entrepreneur> players) {
        List<TokenType> visitors = players.stream()
                .filter(player -> player.getSquareId() == square.getId())
                .map(Entrepreneur::getTokenType)
                .toList();

        Optional<Property> propertyEntityOptional = propertyRetriever.findById(monopolyId, square.getId());
        return propertyEntityOptional
                .map(property -> toSquare(square, property, visitors))
                .orElse(toSquare(square, visitors));
    }

    private SquareDto toSquare(Square square, List<TokenType> visitors) {
        return SquareDto.builder()
                .id(square.getId())
                .name(square.getName())
                .ownedType(square.getOwnedType())
                .visitors(visitors)
                .build();
    }

    private SquareDto toSquare(Square square, Property property, List<TokenType> visitors) {
        Optional<Entrepreneur> entrepreneurOptional = entrepreneurRetriever.findById(property.getEntrepreneurId());
        return toSquare(square, visitors).toBuilder()
                .ownedType(property.getOwnedType())
                .owner(entrepreneurOptional.map(Entrepreneur::getName).orElse(""))
                .build();
    }
}
