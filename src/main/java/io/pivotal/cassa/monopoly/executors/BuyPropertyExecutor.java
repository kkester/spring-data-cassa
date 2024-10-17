package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.SquareDetails;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.property.IPropertyCommand;
import io.pivotal.cassa.property.Property;
import io.pivotal.cassa.property.db.PropertyEntity;
import io.pivotal.cassa.property.db.PropertyKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuyPropertyExecutor {

    private final IPropertyCommand propertyCommand;

    public void handleBuyProperty(UUID monopolyId, Entrepreneur player, SquareDetails square) {
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
