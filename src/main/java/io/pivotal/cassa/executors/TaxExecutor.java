package io.pivotal.cassa.executors;

import io.pivotal.cassa.MonopolyRepository;
import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaxExecutor {

    private final MonopolyRepository monopolyRepository;

    public void processSquare(UUID monopolyId, EntrepreneurEntity player, SquareEntity squareEntity) {
        monopolyRepository.findById(monopolyId).ifPresent(monopolyEntity -> {
            monopolyEntity.setPot(monopolyEntity.getPot() + squareEntity.getTax());
            monopolyRepository.save(monopolyEntity);
        });
        player.setFunds(player.getFunds() - squareEntity.getTax());
    }
}
