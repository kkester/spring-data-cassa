package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.property.IPropertyCommand;
import io.pivotal.cassa.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuyPropertyExecutor {

    private final IPropertyCommand propertyCommand;

    public void handleBuyProperty(UUID monopolyId, Entrepreneur player, Square square) {
        if ((player.getFunds() - square.getPrice()) < 250) {
            return;
        }

        player.setFunds(player.getFunds() - square.getPrice());
        propertyCommand.create(Property.builder()
                .monopolyId(monopolyId)
                .squareId(square.getId())
                .entrepreneurId(player.getId())
                .ownedType(OwnedType.OWNED)
                .build());
    }
}
