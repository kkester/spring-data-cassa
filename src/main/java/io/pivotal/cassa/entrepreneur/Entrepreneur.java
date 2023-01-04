package io.pivotal.cassa.entrepreneur;

import io.pivotal.cassa.board.Square;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Entrepreneur {
    private String name;
    private boolean human;
    private TokenType tokenType;
    private Double funds;
    private Square square;
    private String message;
}
