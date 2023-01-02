package io.pivotal.cassa.executors;

import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import io.pivotal.cassa.property.PropertyEntity;
import io.pivotal.cassa.property.PropertyKey;
import io.pivotal.cassa.property.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuyPropertyExecutor {

    private final PropertyRepository propertyRepository;

    public void handleBuyProperty(UUID monopolyId, EntrepreneurEntity player, SquareEntity square) {
        if ((player.getFunds() - square.getPrice()) < 250) {
            return;
        }

        player.setFunds(player.getFunds() - square.getPrice());
        propertyRepository.save(PropertyEntity.builder()
            .propertyKey(PropertyKey.builder()
                .monopolyId(monopolyId)
                .squareId(square.getId())
                .build())
            .entrepreneurId(player.getId())
            .ownedType(OwnedType.OWNED)
            .build());
    }
}
