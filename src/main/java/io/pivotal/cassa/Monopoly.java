package io.pivotal.cassa;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaBool;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import io.pivotal.cassa.entrepreneur.Players;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSchemaInject(bools = {@JsonSchemaBool(path = "readOnly", value = true)})
public class Monopoly {
    private List<Players> players;
    private Integer pot;
}
