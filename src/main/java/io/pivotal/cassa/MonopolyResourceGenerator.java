package io.pivotal.cassa;

import io.pivotal.cassa.board.Board;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.EntrepreneurGenerator;
import io.pivotal.cassa.entrepreneur.Players;
import io.pivotal.cassa.entrepreneur.TokenType;
import io.pivotal.cassa.mediatype.DriveLink;
import io.pivotal.cassa.mediatype.DriveResource;
import io.pivotal.cassa.mediatype.DriveResourceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Component
@RequiredArgsConstructor
public class MonopolyResourceGenerator {
    private final EntrepreneurGenerator entrepreneurGenerator;
    private final DriveResourceGenerator resourceGenerator;

    public DriveResource<Monopoly> create(@RequestParam TokenType token) {
        Map<String, DriveLink> links = emptyMap();
        Entrepreneur entrepreneur1 = entrepreneurGenerator.create(token);
        Entrepreneur entrepreneur2 = entrepreneurGenerator.createWithAnyTokenExcept(token);
        Monopoly monopoly = Monopoly.builder()
            .players(List.of(Players.builder()
                .player1(entrepreneur1)
                .player2(entrepreneur2)
                .build()))
            .board(Board.builder().build())
            .build();
        return resourceGenerator.createDriveResource(links, monopoly);
    }
}
