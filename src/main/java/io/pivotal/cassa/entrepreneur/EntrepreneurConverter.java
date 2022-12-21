package io.pivotal.cassa.entrepreneur;

import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.board.SquareRepository;
import io.pivotal.cassa.property.PropertyEntity;
import io.pivotal.cassa.property.PropertyKey;
import io.pivotal.cassa.property.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EntrepreneurConverter {
    private final SquareRepository squareRepository;
    private final EntrepreneurRepository entrepreneurRepository;
    private final PropertyRepository propertyRepository;

    public Entrepreneur toEntrepreneur(UUID monopolyId, EntrepreneurEntity entrepreneurEntity) {
        SquareEntity squareEntity = squareRepository.findById(entrepreneurEntity.getSquareId());
        Optional<PropertyEntity> propertyEntityOptional = findProperty(monopolyId, squareEntity);
        Square square = propertyEntityOptional
            .map(propertyEntity -> toSquare(squareEntity.getName(), propertyEntity))
            .orElse(Square.builder()
                .name(squareEntity.getName())
                .ownedType(squareEntity.getOwnedType())
                .build());

        return Entrepreneur.builder()
            .name(entrepreneurEntity.getName())
            .tokenType(entrepreneurEntity.getTokenType())
            .funds(entrepreneurEntity.getFunds())
            .square(square)
            .human(entrepreneurEntity.isHuman())
            .build();
    }

    private Square toSquare(String squareName, PropertyEntity propertyEntity) {
        Optional<EntrepreneurEntity> entrepreneurOptional = entrepreneurRepository.findById(propertyEntity.getEntrepreneurId());
        return Square.builder()
            .name(squareName)
            .owner(entrepreneurOptional.map(EntrepreneurEntity::getName).orElse(""))
            .ownedType(propertyEntity.getOwnedType())
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
