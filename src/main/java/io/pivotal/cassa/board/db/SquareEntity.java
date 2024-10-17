package io.pivotal.cassa.board.db;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaBool;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.SquareDetails;
import io.pivotal.cassa.board.SquareType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonSchemaInject(bools = {@JsonSchemaBool(path = "readOnly", value = true)})
public class SquareEntity implements SquareDetails {
    private Integer id;
    private String name;
    private ColorType color;
    private SquareType type;
    private Integer price;
    private Integer tax;
    private Integer salary;
    private Integer rent;
    private Integer houseRent;
    private Integer houseCost;
    private Integer hotelRent;
    private Integer hotelCost;
    private OwnedType ownedType;
    private String imageName;
}
