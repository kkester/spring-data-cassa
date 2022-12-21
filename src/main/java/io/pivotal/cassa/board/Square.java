package io.pivotal.cassa.board;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaBool;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSchemaInject(bools = {@JsonSchemaBool(path = "readOnly", value = true)})
public class Square {
    private String name;
    private OwnedType ownedType;
    private String owner;
}
