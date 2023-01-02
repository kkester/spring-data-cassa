package io.pivotal.cassa.executors;

import io.pivotal.cassa.MonopolyRepository;
import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FreeParkingExecutor {

    private final MonopolyRepository monopolyRepository;

    public void processSquare(UUID monopolyId, EntrepreneurEntity player, SquareEntity squareEntity) {
        monopolyRepository.findById(monopolyId).ifPresent(monopolyEntity -> {
            player.setFunds(player.getFunds() + monopolyEntity.getPot());
            monopolyEntity.setPot(200);
            monopolyRepository.save(monopolyEntity);
        });
    }
}
