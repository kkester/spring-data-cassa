package io.pivotal.cassa.executors;

import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurRepository;
import io.pivotal.cassa.property.PropertyEntity;
import io.pivotal.cassa.property.PropertyKey;
import io.pivotal.cassa.property.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RailroadExecutor {

    private final EntrepreneurRepository entrepreneurRepository;
    private final PropertyRepository propertyRepository;
    private final BuyPropertyExecutor buyPropertyExecutor;

    public void processSquare(UUID monopolyId, EntrepreneurEntity player, SquareEntity square) {
        propertyRepository.findById(PropertyKey.builder()
                .monopolyId(monopolyId)
                .squareId(square.getId())
                .build())
            .ifPresentOrElse(
                property -> handlePropertyFound(player, square, property),
                () -> handlePropertyNotFound(monopolyId, player, square));
    }

    private void handlePropertyFound(EntrepreneurEntity player, SquareEntity squareEntity, PropertyEntity propertyEntity) {
        UUID entrepreneurId = propertyEntity.getEntrepreneurId();
        if (!entrepreneurId.equals(player.getId())) {
            handlePlayerDoesNotOwnProperty(player, squareEntity, propertyEntity, entrepreneurId);
        }
    }

    private void handlePlayerDoesNotOwnProperty(EntrepreneurEntity player, SquareEntity squareEntity, PropertyEntity propertyEntity, UUID entrepreneurId) {
        entrepreneurRepository.findById(entrepreneurId)
            .ifPresent(owner -> {
                if (OwnedType.OWNED.equals(propertyEntity.getOwnedType())) {
                    player.setFunds(player.getFunds() - squareEntity.getRent());
                    owner.setFunds(owner.getFunds() + squareEntity.getRent());
                }
                entrepreneurRepository.save(owner);
            });
    }

    private void handlePropertyNotFound(UUID monopolyId, EntrepreneurEntity player, SquareEntity square) {
        buyPropertyExecutor.handleBuyProperty(monopolyId, player, square);
    }
}
