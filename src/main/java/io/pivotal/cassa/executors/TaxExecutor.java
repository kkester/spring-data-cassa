package io.pivotal.cassa.executors;

import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import org.springframework.stereotype.Component;

@Component
public class TaxExecutor {
    public void processSquare(EntrepreneurEntity player, SquareEntity squareEntity) {
        player.setFunds(player.getFunds() - squareEntity.getTax());
    }
}
