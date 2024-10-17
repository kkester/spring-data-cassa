package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.monopoly.db.MonopolyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FreeParkingExecutor {

    private final MonopolyRepository monopolyRepository;

    public void processSquare(UUID monopolyId, Entrepreneur player) {
        monopolyRepository.findById(monopolyId).ifPresent(monopolyEntity -> {
            player.setFunds(player.getFunds() + monopolyEntity.getPot());
            monopolyEntity.setPot(200);
            monopolyRepository.save(monopolyEntity);
        });
    }
}
