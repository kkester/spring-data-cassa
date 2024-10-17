package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.SquareDetails;
import io.pivotal.cassa.board.SquareType;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurRetriever;
import io.pivotal.cassa.property.IPropertyRetriever;
import io.pivotal.cassa.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RailroadExecutor {

    private final IEntrepreneurRetriever entrepreneurRetriever;
    private final IPropertyRetriever propertyRetriever;
    private final ISquareRetriever squareRetriever;
    private final BuyPropertyExecutor buyPropertyExecutor;

    public void processSquare(UUID monopolyId, Entrepreneur player, SquareDetails square) {
        propertyRetriever.findById(monopolyId, square.getId())
            .ifPresentOrElse(
                property -> handlePropertyFound(player, square, property),
                () -> handlePropertyNotFound(monopolyId, player, square));
    }

    private void handlePropertyFound(Entrepreneur player, SquareDetails squareEntity, Property property) {
        UUID entrepreneurId = property.getEntrepreneurId();
        if (!entrepreneurId.equals(player.getId())) {
            handlePlayerDoesNotOwnProperty(player, squareEntity, property, entrepreneurId);
        }
    }

    private void handlePlayerDoesNotOwnProperty(Entrepreneur player, SquareDetails squareEntity, Property propertyEntity, UUID entrepreneurId) {
        SquareType squareType = squareEntity.getType();
        long propertyTypeCount = propertyRetriever.findAllByEntrepreneurId(entrepreneurId).stream()
            .filter(playerProperty -> squareType.equals(squareRetriever.getSquareDetailsById(playerProperty.getSquareId()).getType()))
            .count();
        entrepreneurRetriever.findById(entrepreneurId)
            .ifPresent(owner -> {
                if (OwnedType.OWNED.equals(propertyEntity.getOwnedType())) {
                    int rentCost = squareEntity.getRent() * (int) propertyTypeCount;
                    player.setFunds(player.getFunds() - rentCost);
                    owner.setFunds(owner.getFunds() + rentCost);
                }
            });
    }

    private void handlePropertyNotFound(UUID monopolyId, Entrepreneur player, SquareDetails square) {
        buyPropertyExecutor.handleBuyProperty(monopolyId, player, square);
    }
}
