package io.pivotal.cassa.executors;

import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.board.SquareRepository;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import io.pivotal.cassa.property.PropertyEntity;
import io.pivotal.cassa.property.PropertyRepository;
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

    private final PropertyRepository propertyRepository;
    private final SquareRepository squareRepository;

    public void handleBankruptcy(EntrepreneurEntity player) {
        List<PropertyEntity> properties = propertyRepository.findAllByEntrepreneurId(player.getId())
            .stream().sorted(Comparator.comparing(p -> p.getPropertyKey().getSquareId())).toList();
        for (PropertyEntity propertyEntity : properties) {
            sellProperty(player, propertyEntity);
            if (player.getFunds() > 0) {
                break;
            }
        }
        ;
    }

    private void sellProperty(EntrepreneurEntity player, PropertyEntity propertyEntity) {
        SquareEntity squareEntity = squareRepository.findById(propertyEntity.getPropertyKey().getSquareId());
        if (HOTEL.equals(propertyEntity.getOwnedType())) {
            Integer salePrice = calculateSalePrice(squareEntity.getHotelCost());
            player.setFunds(player.getFunds() + salePrice);
            propertyEntity.setOwnedType(HOUSE);
        }
        if (player.getFunds() < 1 && HOUSE.equals(propertyEntity.getOwnedType())) {
            Integer salePrice = calculateSalePrice(squareEntity.getHouseCost());
            player.setFunds(player.getFunds() + salePrice);
            propertyEntity.setOwnedType(OWNED);
        }
        if (player.getFunds() < 1 && OWNED.equals(propertyEntity.getOwnedType())) {
            Integer salePrice = calculateSalePrice(squareEntity.getPrice());
            player.setFunds(player.getFunds() + salePrice);
            propertyEntity.setOwnedType(FOR_SALE);
        }
        if (FOR_SALE.equals(propertyEntity.getOwnedType())) {
            propertyRepository.delete(propertyEntity);
        } else {
            propertyRepository.save(propertyEntity);
        }
    }

    private Integer calculateSalePrice(Integer hotelCost) {
        return BigDecimal.valueOf(hotelCost).multiply(FACTOR, MathContext.UNLIMITED).intValue();
    }
}
