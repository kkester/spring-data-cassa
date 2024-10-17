package io.pivotal.cassa.monopoly;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaBool;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSchemaInject(bools = {@JsonSchemaBool(path = "readOnly", value = true)})
public class Monopoly {
    private UUID id;
    private List<Entrepreneur> players;
    private Integer pot;
    private boolean gameOver;
    private List<Square> squares;
}
