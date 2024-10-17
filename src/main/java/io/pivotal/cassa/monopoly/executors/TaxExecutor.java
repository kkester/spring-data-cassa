package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.board.SquareDetails;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.monopoly.db.MonopolyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaxExecutor {

    private final MonopolyRepository monopolyRepository;

    public void processSquare(UUID monopolyId, Entrepreneur player, SquareDetails square) {
        monopolyRepository.findById(monopolyId).ifPresent(monopolyEntity -> {
            monopolyEntity.setPot(monopolyEntity.getPot() + square.getTax());
            monopolyRepository.save(monopolyEntity);
        });
        player.setFunds(player.getFunds() - square.getTax());
    }
}
