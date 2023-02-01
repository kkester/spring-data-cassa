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
import java.util.stream.Collectors;

import static io.pivotal.cassa.board.SquareType.*;

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
    private final FreeParkingExecutor freeParkingExecutor;
    private final Faker faker;

    public void rollDice(UUID monopolyId) {
        List<UUID> playerIds = entrepreneurRepository.findAllByMonopolyId(monopolyId)
            .stream().map(EntrepreneurEntity::getId)
            .collect(Collectors.toList());
        playerIds.forEach(playerId ->
            entrepreneurRepository.findById(playerId)
                .ifPresent(player -> {
                    Integer roll = faker.random().nextInt(2, 12);
                    int nextSquare = player.getSquareId() + roll;
                    if (nextSquare > 30) {
                        nextSquare = nextSquare - 30;
                        player.setFunds(player.getFunds() + 200);
                    }
                    player.setMessage(null);
                    player.setSquareId(nextSquare);
                    invokeSquareExecutor(monopolyId, player, nextSquare);
                    entrepreneurRepository.save(player);
                })
        );
    }

    private void invokeSquareExecutor(UUID monopolyId, EntrepreneurEntity player, int squareId) {
        SquareEntity squareEntity = squareRepository.findById(squareId);
        switch (squareEntity.getType()) {
            case CHANCE -> chanceExecutor.processSquare(player);
            case CHEST -> chestExecutor.processSquare(player);
            case TAX -> taxExecutor.processSquare(monopolyId, player, squareEntity);
            case RAILROAD, UTILITY -> railroadExecutor.processSquare(monopolyId, player, squareEntity);
            case PARKING -> freeParkingExecutor.processSquare(monopolyId, player, squareEntity);
            case PROPERTY -> propertyExecutor.processSquare(monopolyId, player, squareEntity);
            default -> {}
        }
    }
}
