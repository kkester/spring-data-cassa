package io.pivotal.cassa;

import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MonopolyExecutor {
    private final EntrepreneurRepository entrepreneurRepository;
    private final Faker faker;

    public void rollDice(UUID monopolyId) {
        List<EntrepreneurEntity> players = entrepreneurRepository.findAllByMonopolyId(monopolyId);
        players.forEach(player -> {
            Integer roll = faker.random().nextInt(2, 12);
            int nextSquare = player.getSquareId() + roll;
            if (nextSquare > 20) {
                nextSquare = nextSquare - 20;
                player.setFunds(player.getFunds() + 400);
            } else if (nextSquare > 10) {
                nextSquare = nextSquare - 10;
                player.setFunds(player.getFunds() + 200);
            }
            player.setSquareId(nextSquare);
            entrepreneurRepository.save(player);
        });
    }
}
