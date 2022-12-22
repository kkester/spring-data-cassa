package io.pivotal.cassa.executors;

import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.board.SquareRepository;
import io.pivotal.cassa.board.SquareType;
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
    private final SquareRepository squareRepository;
    private final PropertyExecutor propertyExecutor;
    private final ChestExecutor chestExecutor;
    private final ChanceExecutor chanceExecutor;
    private final TaxExecutor taxExecutor;
    private final RailroadExecutor railroadExecutor;
    private final Faker faker;

    public void rollDice(UUID monopolyId) {
        List<EntrepreneurEntity> players = entrepreneurRepository.findAllByMonopolyId(monopolyId);
        players.forEach(player -> {
            Integer roll = faker.random().nextInt(2, 12);
            int nextSquare = player.getSquareId() + roll;
            if (nextSquare > 20) {
                nextSquare = nextSquare - 20;
                player.setFunds(player.getFunds() + 100);
            }
            player.setSquareId(nextSquare);
            invokeSquareExecutor(monopolyId, player, nextSquare);
            entrepreneurRepository.save(player);
        });
    }

    private void invokeSquareExecutor(UUID monopolyId, EntrepreneurEntity player, int squareId) {
        SquareEntity squareEntity = squareRepository.findById(squareId);
        if (SquareType.CHANCE.equals(squareEntity.getType())) {
            chanceExecutor.processSquare(player, squareEntity);
        } else if (SquareType.CHEST.equals(squareEntity.getType())) {
            chestExecutor.processSquare(player, squareEntity);
        } else if (SquareType.TAX.equals(squareEntity.getType())) {
            taxExecutor.processSquare(player, squareEntity);
        } else if (SquareType.PROPERTY.equals(squareEntity.getType())) {
            propertyExecutor.processSquare(monopolyId, player, squareEntity);
        } else if (SquareType.RAILROAD.equals(squareEntity.getType()) || SquareType.UTILITY.equals(squareEntity.getType())) {
            railroadExecutor.processSquare(monopolyId, player, squareEntity);
        }
    }
}
