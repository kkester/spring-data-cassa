package io.pivotal.cassa;

import io.pivotal.cassa.board.Board;
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
public class Monopoly {
    private List<Players> players;
    private Board board;
}
