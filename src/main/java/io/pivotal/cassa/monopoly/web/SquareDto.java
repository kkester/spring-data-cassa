package io.pivotal.cassa.monopoly.web;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaBool;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSchemaInject(bools = {@JsonSchemaBool(path = "readOnly", value = true)})
public class SquareDto {
    private Integer id;
    private String name;
    private OwnedType ownedType;
    private String owner;
    private List<TokenType> visitors;
}
