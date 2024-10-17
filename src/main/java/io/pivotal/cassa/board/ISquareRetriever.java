package io.pivotal.cassa.board;

import java.util.List;
import java.util.UUID;

public interface ISquareRetriever {
    List<Square> retrieveSquares(UUID monopolyEntityId);
    SquareDetails getSquareDetailsById(Integer squareId);
    SquareDetails getSquareDetailsByName(String name);
    Square getSquareById(Integer squareId);
}
