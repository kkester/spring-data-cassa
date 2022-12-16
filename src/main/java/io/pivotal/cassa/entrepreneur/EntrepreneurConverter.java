package io.pivotal.cassa.entrepreneur;

import io.pivotal.cassa.board.SquareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntrepreneurConverter {
    private final SquareRepository squareRepository;

    public Entrepreneur toEntrepreneur(EntrepreneurEntity entrepreneurEntity) {
        return Entrepreneur.builder()
            .name(entrepreneurEntity.getName())
            .tokenType(entrepreneurEntity.getTokenType())
            .funds(entrepreneurEntity.getFunds())
            .square(squareRepository.findById(entrepreneurEntity.getSquareId()))
            .build();
    }
}
