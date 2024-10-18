package io.pivotal.cassa.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Square {
    private Integer id;
    private String name;
    private SquareType type;
    private Integer price;
    private Integer tax;
    private Integer rent;
    private Integer houseRent;
    private Integer houseCost;
    private Integer hotelRent;
    private Integer hotelCost;
    private OwnedType ownedType;
    private String imageName;
}
