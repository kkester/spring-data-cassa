package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.board.SquareDetails;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurCommand;
import io.pivotal.cassa.entrepreneur.IEntrepreneurRetriever;
import io.pivotal.cassa.monopoly.db.MonopolyRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MonopolyExecutor {

    private final MonopolyRepository monopolyRepository;
    private final IEntrepreneurRetriever entrepreneurRetriever;
    private final IEntrepreneurCommand entrepreneurCommand;
    private final ISquareRetriever squareRetriever;

    private final PropertyExecutor propertyExecutor;
    private final ChestExecutor chestExecutor;
    private final ChanceExecutor chanceExecutor;
    private final TaxExecutor taxExecutor;
    private final RailroadExecutor railroadExecutor;
    private final FreeParkingExecutor freeParkingExecutor;
    private final BankruptExecutor bankruptExecutor;
    private final Faker faker;

    public void rollDice(UUID monopolyId) {
        List<UUID> playerIds = entrepreneurRetriever.retrieveEntrepreneurs(monopolyId)
            .stream().map(Entrepreneur::getId).toList();
        playerIds.forEach(playerId ->
            entrepreneurRetriever.findById(playerId)
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
                        entrepreneurCommand.update(player);
                    }
                })
        );
        evaluateGameStatus(monopolyId, playerIds);
    }

    private void invokeSquareExecutor(UUID monopolyId, Entrepreneur player, int squareId) {
        SquareDetails square = squareRetriever.getSquareDetailsById(squareId);
        switch (square.getType()) {
            case CHANCE -> chanceExecutor.processSquare(player);
            case CHEST -> chestExecutor.processSquare(player);
            case TAX -> taxExecutor.processSquare(monopolyId, player, square);
            case RAILROAD, UTILITY -> railroadExecutor.processSquare(monopolyId, player, square);
            case PARKING -> freeParkingExecutor.processSquare(monopolyId, player);
            case PROPERTY -> propertyExecutor.processSquare(monopolyId, player, square);
            default -> {
            }
        }
    }

    private void evaluateGameStatus(UUID monopolyId, List<UUID> playerIds) {
        List<Entrepreneur> players = playerIds.stream()
            .map(entrepreneurRetriever::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
        Integer humanFunds = players.stream()
            .filter(Entrepreneur::isHuman)
            .findFirst()
            .map(Entrepreneur::getFunds)
            .orElse(0);
        long count = players.stream().filter(p -> p.getFunds() > 0).count();
        boolean gameOver = humanFunds < 1 || count == 1;
        monopolyRepository.findById(monopolyId).ifPresent(monopolyEntity -> {
            monopolyEntity.setGameOver(gameOver);
            monopolyRepository.save(monopolyEntity);
        });
    }
}
