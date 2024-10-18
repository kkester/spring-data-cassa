package io.pivotal.cassa.board.components;

import io.pivotal.cassa.board.ISquareRetriever;
import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.board.db.SquareEntity;
import io.pivotal.cassa.board.db.SquareRepository;
import io.pivotal.cassa.entrepreneur.IEntrepreneurRetriever;
import io.pivotal.cassa.property.IPropertyRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SquareRetriever implements ISquareRetriever {

    private final SquareRepository squareRepository;
    private final IEntrepreneurRetriever entrepreneurRetriever;
    private final IPropertyRetriever propertyRetriever;

    @Override
    public List<Square> getAllSquares() {
        return squareRepository.findAll().stream()
                .map(this::toSquare)
                .toList();
    }

    @Override
    public Square getSquareById(Integer squareId) {
        return toSquare(squareRepository.findById(squareId));
    }

    @Override
    public Square getSquareByName(String name) {
        return toSquare(squareRepository.findByName(name));
    }

    private Square toSquare(SquareEntity squareEntity) {
        return Square.builder()
                .id(squareEntity.getId())
                .name(squareEntity.getName())
                .type(squareEntity.getType())
                .price(squareEntity.getPrice())
                .rent(squareEntity.getRent())
                .tax(squareEntity.getTax())
                .houseCost(squareEntity.getHouseCost())
                .houseRent(squareEntity.getHouseRent())
                .hotelCost(squareEntity.getHotelCost())
                .hotelRent(squareEntity.getHotelRent())
                .ownedType(squareEntity.getOwnedType())
                .imageName(squareEntity.getImageName())
                .build();
    }
}
