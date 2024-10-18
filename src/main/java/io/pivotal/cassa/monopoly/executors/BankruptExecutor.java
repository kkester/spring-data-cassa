package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.property.IPropertyCommand;
import io.pivotal.cassa.property.IPropertyRetriever;
import io.pivotal.cassa.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.List;

import static io.pivotal.cassa.board.OwnedType.*;

@Component
@RequiredArgsConstructor
public class BankruptExecutor {

    private static final BigDecimal FACTOR = BigDecimal.valueOf(0.75);

    private final IPropertyRetriever propertyRetriever;
    private final IPropertyCommand propertyCommand;
    private final ISquareRetriever squareRetriever;

    public void handleBankruptcy(Entrepreneur player) {
        List<Property> properties = propertyRetriever.findAllByEntrepreneurId(player.getId()).stream()
                .sorted(Comparator.comparing(Property::getSquareId))
                .toList();
        for (Property property : properties) {
            sellProperty(player, property);
            if (player.getFunds() > 0) {
                break;
            }
        }
    }

    private void sellProperty(Entrepreneur player, Property property) {
        Square square = squareRetriever.getSquareById(property.getSquareId());
        if (HOTEL.equals(property.getOwnedType())) {
            Integer salePrice = calculateSalePrice(square.getHotelCost());
            player.setFunds(player.getFunds() + salePrice);
            property.setOwnedType(HOUSE);
        }
        if (player.getFunds() < 1 && HOUSE.equals(property.getOwnedType())) {
            Integer salePrice = calculateSalePrice(square.getHouseCost());
            player.setFunds(player.getFunds() + salePrice);
            property.setOwnedType(OWNED);
        }
        if (player.getFunds() < 1 && OWNED.equals(property.getOwnedType())) {
            Integer salePrice = calculateSalePrice(square.getPrice());
            player.setFunds(player.getFunds() + salePrice);
            property.setOwnedType(FOR_SALE);
        }
        if (FOR_SALE.equals(property.getOwnedType())) {
            propertyCommand.delete(property);
        } else {
            propertyCommand.update(property);
        }
    }

    private Integer calculateSalePrice(Integer hotelCost) {
        return BigDecimal.valueOf(hotelCost).multiply(FACTOR, MathContext.UNLIMITED).intValue();
    }
}
