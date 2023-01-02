package io.pivotal.cassa.executors;

import io.pivotal.cassa.property.PropertyEntity;
import io.pivotal.cassa.property.PropertyKey;
import io.pivotal.cassa.property.PropertyRepository;
import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.SquareEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PropertyExecutor {

    private final EntrepreneurRepository entrepreneurRepository;
    private final PropertyRepository propertyRepository;

    public void processSquare(UUID monopolyId, EntrepreneurEntity player, SquareEntity square) {
        propertyRepository.findById(PropertyKey.builder()
                .monopolyId(monopolyId)
                .squareId(square.getId())
                .build())
            .ifPresentOrElse(
                property -> handlePropertyFound(player, square, property),
                () -> handlePropertyNotFound(monopolyId, player, square));
    }

    private void handlePropertyFound(EntrepreneurEntity player, SquareEntity square, PropertyEntity property) {
        UUID entrepreneurId = property.getEntrepreneurId();
        if (entrepreneurId.equals(player.getId())) {
            handlePlayerOwnsProperty(player, square, property);
        } else {
            handlePlayerDoesNotOwnProperty(player, square, property, entrepreneurId);
        }
    }

    private void handlePlayerOwnsProperty(EntrepreneurEntity player, SquareEntity square, PropertyEntity property) {
        Double funds = player.getFunds();
        if (OwnedType.OWNED.equals(property.getOwnedType()) && (funds - square.getHouseCost()) > 250) {
            property.setOwnedType(OwnedType.HOUSE);
            player.setFunds(funds - square.getHouseCost());
        } else if (OwnedType.HOUSE.equals(property.getOwnedType()) && (funds - square.getHotelCost()) > 250) {
            property.setOwnedType(OwnedType.HOTEL);
            player.setFunds(funds - square.getHotelCost());
        }
        propertyRepository.save(property);
    }

    private void handlePlayerDoesNotOwnProperty(EntrepreneurEntity player, SquareEntity square, PropertyEntity property, UUID entrepreneurId) {
        entrepreneurRepository.findById(entrepreneurId)
            .ifPresent(owner -> {
                if (OwnedType.OWNED.equals(property.getOwnedType())) {
                    player.setFunds(player.getFunds() - square.getRent());
                    owner.setFunds(owner.getFunds() + square.getRent());
                } else if (OwnedType.HOUSE.equals(property.getOwnedType())) {
                    player.setFunds(player.getFunds() - square.getHouseRent());
                    owner.setFunds(owner.getFunds() + square.getHouseRent());
                } else if (OwnedType.HOTEL.equals(property.getOwnedType())) {
                    player.setFunds(player.getFunds() - square.getHotelRent());
                    owner.setFunds(owner.getFunds() + square.getHotelRent());
                }
                entrepreneurRepository.save(owner);
            });
    }

    private void handlePropertyNotFound(UUID monopolyId, EntrepreneurEntity player, SquareEntity square) {
        if ((player.getFunds() - square.getPrice()) < 250) {
            return;
        }

        player.setFunds(player.getFunds() - square.getPrice());
        propertyRepository.save(PropertyEntity.builder()
            .propertyKey(PropertyKey.builder()
                .monopolyId(monopolyId)
                .squareId(square.getId())
                .build())
            .entrepreneurId(player.getId())
            .ownedType(OwnedType.OWNED)
            .build());
    }
}
