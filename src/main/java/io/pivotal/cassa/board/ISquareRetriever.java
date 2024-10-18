package io.pivotal.cassa.board;

import java.util.List;

public interface ISquareRetriever {
    Square getSquareById(Integer squareId);
    Square getSquareByName(String name);
    List<Square> getAllSquares();
}
