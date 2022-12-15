package io.pivotal.cassa.entrepreneur;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Players {
    private Entrepreneur player1;
    private Entrepreneur player2;
    private Entrepreneur player3;
}
