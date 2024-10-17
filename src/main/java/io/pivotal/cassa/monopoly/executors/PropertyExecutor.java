package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.board.SquareDetails;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurCommand;
import io.pivotal.cassa.entrepreneur.IEntrepreneurRetriever;
import io.pivotal.cassa.property.IPropertyCommand;
import io.pivotal.cassa.property.IPropertyRetriever;
import io.pivotal.cassa.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static io.pivotal.cassa.board.OwnedType.*;

@Component
@RequiredArgsConstructor
public class PropertyExecutor {

    private final IEntrepreneurRetriever entrepreneurRetriever;
    private final IEntrepreneurCommand entrepreneurCommand;
    private final IPropertyRetriever propertyRepository;
    private final IPropertyCommand propertyCommand;
    private final BuyPropertyExecutor buyPropertyExecutor;

    public void processSquare(UUID monopolyId, Entrepreneur player, SquareDetails square) {
        propertyRepository.findById(monopolyId, square.getId())
            .ifPresentOrElse(
                property -> handlePropertyFound(player, square, property),
                () -> handlePropertyNotFound(monopolyId, player, square));
    }

    private void handlePropertyFound(Entrepreneur player, SquareDetails square, Property property) {
        UUID entrepreneurId = property.getEntrepreneurId();
        if (entrepreneurId.equals(player.getId())) {
            handlePlayerOwnsProperty(player, square, property);
        } else {
            handlePlayerDoesNotOwnProperty(player, square, property, entrepreneurId);
        }
    }

    private void handlePlayerOwnsProperty(Entrepreneur player, SquareDetails square, Property property) {
        Integer funds = player.getFunds();
        if (OWNED.equals(property.getOwnedType()) && (funds - square.getHouseCost()) > 250) {
            property.setOwnedType(HOUSE);
            player.setFunds(funds - square.getHouseCost());
        } else if (HOUSE.equals(property.getOwnedType()) && (funds - square.getHotelCost()) > 250) {
            property.setOwnedType(HOTEL);
            player.setFunds(funds - square.getHotelCost());
        }
        propertyCommand.update(property);
    }

    private void handlePlayerDoesNotOwnProperty(Entrepreneur player, SquareDetails square, Property property, UUID entrepreneurId) {
        entrepreneurRetriever.findById(entrepreneurId)
            .ifPresent(owner -> {
                Integer rent = 0;
                Integer ownerFunds = owner.getFunds();
                Integer playerFunds = player.getFunds();
                if (OWNED.equals(property.getOwnedType())) {
                    rent = square.getRent();
                    player.setFunds(playerFunds - square.getRent());
                    owner.setFunds(ownerFunds + square.getRent());
                } else if (HOUSE.equals(property.getOwnedType())) {
                    rent = square.getHouseRent();
                    player.setFunds(playerFunds - square.getHouseRent());
                    owner.setFunds(ownerFunds + square.getHouseRent());
                } else if (HOTEL.equals(property.getOwnedType())) {
                    rent = square.getHotelRent();
                    player.setFunds(playerFunds - square.getHotelRent());
                    owner.setFunds(ownerFunds + square.getHotelRent());
                }
                owner.setMessage(player.getName() + "($" + playerFunds + ") pays " + owner.getName() + "($" + ownerFunds + ") " + rent + " for rent!!!");
                entrepreneurCommand.update(owner);
            });
    }

    private void handlePropertyNotFound(UUID monopolyId, Entrepreneur player, SquareDetails square) {
        buyPropertyExecutor.handleBuyProperty(monopolyId, player, square);
    }
}
