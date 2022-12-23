package io.pivotal.cassa.board;

import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurRepository;
import io.pivotal.cassa.entrepreneur.TokenType;
import io.pivotal.cassa.property.PropertyEntity;
import io.pivotal.cassa.property.PropertyKey;
import io.pivotal.cassa.property.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SquareRetriever {
    private final SquareRepository squareRepository;
    private final EntrepreneurRepository entrepreneurRepository;
    private final PropertyRepository propertyRepository;

    public List<Square> retrieveSquares(UUID monopolyId) {
        List<EntrepreneurEntity> players = entrepreneurRepository.findAllByMonopolyId(monopolyId);
        return squareRepository.findAll().stream()
            .map(squareEntity -> getSquareFor(monopolyId, squareEntity.getId(), players))
            .collect(Collectors.toList());
    }

    public Square getSquareFor(UUID monopolyId, int squareId, List<EntrepreneurEntity> players) {
        List<TokenType> visitors = players.stream()
            .filter(player -> player.getSquareId().equals(squareId))
            .map(EntrepreneurEntity::getTokenType)
            .collect(Collectors.toList());

        SquareEntity squareEntity = squareRepository.findById(squareId);
        Optional<PropertyEntity> propertyEntityOptional = findProperty(monopolyId, squareEntity);
        return propertyEntityOptional
            .map(propertyEntity -> toSquare(squareEntity.getName(), propertyEntity, visitors))
            .orElse(Square.builder()
                .name(squareEntity.getName())
                .ownedType(squareEntity.getOwnedType())
                .visitors(visitors)
                .build());
    }

    private Square toSquare(String squareName, PropertyEntity propertyEntity, List<TokenType> visitors) {
        Optional<EntrepreneurEntity> entrepreneurOptional = entrepreneurRepository.findById(propertyEntity.getEntrepreneurId());
        return Square.builder()
            .name(squareName)
            .owner(entrepreneurOptional.map(EntrepreneurEntity::getName).orElse(""))
            .ownedType(propertyEntity.getOwnedType())
            .visitors(visitors)
            .build();
    }

    private Optional<PropertyEntity> findProperty(UUID monopolyId, SquareEntity squareEntity) {
        return propertyRepository.findById(
            PropertyKey.builder()
                .monopolyId(monopolyId)
                .squareId(squareEntity.getId())
                .build());
    }
}
