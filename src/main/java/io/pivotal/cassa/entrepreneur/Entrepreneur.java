package io.pivotal.cassa.entrepreneur;

import io.pivotal.cassa.board.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Entrepreneur {
    private UUID id;
    private String name;
    private boolean human;
    private TokenType tokenType;
    private Integer funds;
    private int squareId;
    private String message;
}
