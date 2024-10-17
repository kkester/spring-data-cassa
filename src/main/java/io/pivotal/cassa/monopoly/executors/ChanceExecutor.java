package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurEntity;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChanceExecutor {

    private final Faker faker;

    public void processSquare(Entrepreneur player) {
        int toss = faker.random().nextInt(2);
        int value = faker.random().nextInt(50, 200);
        if (toss == 0) {
            player.setFunds(player.getFunds() + value);
            player.setMessage(player.getName() + ", You won first prize at a beauty contest.  Collect $" + value);
        } else {
            player.setFunds(player.getFunds() - value);
            player.setMessage(player.getName() + ", Doctor's Fee.  Pay $" + value);
        }
    }
}
