package io.pivotal.cassa.board.components;

import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.board.SquareDetails;
import io.pivotal.cassa.board.TokenType;
import io.pivotal.cassa.board.db.SquareEntity;
import io.pivotal.cassa.board.db.SquareRepository;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurRetriever;
import io.pivotal.cassa.property.IPropertyRetriever;
import io.pivotal.cassa.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SquareRetriever implements ISquareRetriever {

    private final SquareRepository squareRepository;
    private final IEntrepreneurRetriever entrepreneurRetriever;
    private final IPropertyRetriever propertyRetriever;

    public List<Square> retrieveSquares(UUID monopolyId) {
        List<Entrepreneur> players = entrepreneurRetriever.retrieveEntrepreneurs(monopolyId);
        return squareRepository.findAll().stream()
                .map(squareEntity -> getSquareFor(monopolyId, squareEntity.getId(), players))
                .toList();
    }

    @Override
    public SquareDetails getSquareDetailsById(Integer squareId) {
        return squareRepository.findById(squareId);
    }

    @Override
    public SquareDetails getSquareDetailsByName(String name) {
        return squareRepository.findByName(name);
    }

    @Override
    public Square getSquareById(Integer squareId) {
        return toSquare(squareRepository.findById(squareId));
    }

    private Square toSquare(SquareEntity squareEntity) {
        return Square.builder()
                .id(squareEntity.getId())
                .name(squareEntity.getName())
                .ownedType(squareEntity.getOwnedType())
                .build();
    }

    private Square getSquareFor(UUID monopolyId, int squareId, List<Entrepreneur> players) {
        List<TokenType> visitors = players.stream()
                .filter(player -> player.getSquareId() == squareId)
                .map(Entrepreneur::getTokenType)
                .toList();

        SquareEntity squareEntity = squareRepository.findById(squareId);
        Optional<Property> propertyEntityOptional = findProperty(monopolyId, squareEntity);
        return propertyEntityOptional
                .map(property -> toSquare(squareEntity, property, visitors))
                .orElse(Square.builder()
                        .id(squareEntity.getId())
                        .name(squareEntity.getName())
                        .ownedType(squareEntity.getOwnedType())
                        .visitors(visitors)
                        .build());
    }

    private Square toSquare(SquareEntity squareEntity, Property property, List<TokenType> visitors) {
        Optional<Entrepreneur> entrepreneurOptional = entrepreneurRetriever.findById(property.getEntrepreneurId());
        return Square.builder()
                .id(squareEntity.getId())
                .name(squareEntity.getName())
                .owner(entrepreneurOptional.map(Entrepreneur::getName).orElse(""))
                .ownedType(property.getOwnedType())
                .visitors(visitors)
                .build();
    }

    private Optional<Property> findProperty(UUID monopolyId, SquareEntity squareEntity) {
        return propertyRetriever.findById(monopolyId, squareEntity.getId());
    }
}
