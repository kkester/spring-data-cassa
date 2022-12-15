package io.pivotal.cassa.entrepreneur;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaBool;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;
import io.pivotal.cassa.board.Square;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSchemaInject(bools = {@JsonSchemaBool(path = "readOnly", value = true)})
public class Entrepreneur {
    @JsonSchemaTitle("Player Name")
    private String name;
    @JsonSchemaTitle("Token")
    private TokenType tokenType;
    @JsonSchemaTitle("Funds")
    private Double funds;
    @JsonSchemaTitle("Square")
    private String square;
}
