package io.pivotal.cassa.executors;

import io.pivotal.cassa.MonopolyRepository;
import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.board.SquareRepository;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MonopolyExecutor {
    private final EntrepreneurRepository entrepreneurRepository;
    private final MonopolyRepository monopolyRepository;
    private final SquareRepository squareRepository;
    private final PropertyExecutor propertyExecutor;
    private final ChestExecutor chestExecutor;
    private final ChanceExecutor chanceExecutor;
    private final TaxExecutor taxExecutor;
    private final RailroadExecutor railroadExecutor;
    private final FreeParkingExecutor freeParkingExecutor;
    private final BankruptExecutor bankruptExecutor;
    private final Faker faker;

    public void rollDice(UUID monopolyId) {
        List<UUID> playerIds = entrepreneurRepository.findAllByMonopolyId(monopolyId)
            .stream().map(EntrepreneurEntity::getId).toList();
        playerIds.forEach(playerId ->
            entrepreneurRepository.findById(playerId)
                .ifPresent(player -> {
                    if (player.getFunds() > 0) {
                        Integer roll = faker.random().nextInt(2, 12);
                        int nextSquare = player.getSquareId() + roll;
                        if (nextSquare > 40) {
                            nextSquare = nextSquare - 40;
                            player.setFunds(player.getFunds() + 200);
                        }
                        player.setMessage(null);
                        player.setSquareId(nextSquare);
                        invokeSquareExecutor(monopolyId, player, nextSquare);
                        if (player.getFunds() < 1) {
                            bankruptExecutor.handleBankruptcy(player);
                        }
                        entrepreneurRepository.save(player);
                    }
                })
        );
        evaluateGameStatus(monopolyId, playerIds);
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
            default -> {
            }
        }
    }

    private void evaluateGameStatus(UUID monopolyId, List<UUID> playerIds) {
        List<EntrepreneurEntity> players = playerIds.stream()
            .map(entrepreneurRepository::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
        Integer humanFunds = players.stream()
            .filter(EntrepreneurEntity::isHuman)
            .findFirst()
            .map(EntrepreneurEntity::getFunds)
            .orElse(0);
        long count = players.stream().filter(p -> p.getFunds() > 0).count();
        boolean gameOver = humanFunds < 1 || count == 1;
        monopolyRepository.findById(monopolyId).ifPresent(monopolyEntity -> {
            monopolyEntity.setGameOver(gameOver);
            monopolyRepository.save(monopolyEntity);
        });
    }
}
