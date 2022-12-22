package io.pivotal.cassa.entrepreneur;

import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.board.SquareRepository;
import io.pivotal.cassa.board.SquareRetriever;
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

    private final SquareRetriever squareRetriever;

    public Entrepreneur toEntrepreneur(UUID monopolyId, EntrepreneurEntity entrepreneurEntity) {
        Square square = squareRetriever.getSquareFor(monopolyId, entrepreneurEntity.getSquareId());
        return Entrepreneur.builder()
            .name(entrepreneurEntity.getName())
            .tokenType(entrepreneurEntity.getTokenType())
            .funds(entrepreneurEntity.getFunds())
            .square(square)
            .human(entrepreneurEntity.isHuman())
            .build();
    }
}
