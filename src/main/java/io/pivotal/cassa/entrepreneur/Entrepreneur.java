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
    private TokenType tokenType;
    private Double funds;
    private String square;
}
