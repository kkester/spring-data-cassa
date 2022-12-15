package io.pivotal.cassa.board;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaBool;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonSchemaInject(bools = {@JsonSchemaBool(path = "readOnly", value = true)})
public class Square {
    private String name;
    private ColorType color;
    private SquareType type;
    private Integer price;
    private Integer tax;
    private Integer salary;
}
