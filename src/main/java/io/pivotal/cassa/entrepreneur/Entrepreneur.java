package io.pivotal.cassa.entrepreneur;

import io.pivotal.cassa.board.Square;
import io.pivotal.cassa.property.Property;
import lombok.*;

import java.util.List;

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
    private List<Property> properties;
}
