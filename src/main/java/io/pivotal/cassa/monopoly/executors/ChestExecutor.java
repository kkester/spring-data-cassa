package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurEntity;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChestExecutor {

    private final Faker faker;

    public void processSquare(Entrepreneur player) {
        int toss = faker.random().nextInt(2);
        int value = faker.random().nextInt(50, 200);
        if (toss == 0) {
            player.setFunds(player.getFunds() - value);
            player.setMessage(player.getName() + ", Pay poor tax of $" + value);
        } else {
            player.setFunds(player.getFunds() + value);
            player.setMessage(player.getName() + ", Bank pays you dividend of $" + value);
        }
    }
}
