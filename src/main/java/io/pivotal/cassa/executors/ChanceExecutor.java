package io.pivotal.cassa.executors;

import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChanceExecutor {
    private final Faker faker;
    public void processSquare(EntrepreneurEntity player, SquareEntity squareEntity) {
        int toss = faker.random().nextInt(2);
        int value = faker.random().nextInt(50, 500);
        if (toss == 0) {
            player.setFunds(player.getFunds() + value);
        } else {
            player.setFunds(player.getFunds() - value);
        }
    }
}
