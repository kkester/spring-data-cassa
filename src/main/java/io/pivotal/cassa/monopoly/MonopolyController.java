package io.pivotal.cassa.monopoly;

import io.pivotal.cassa.board.Board;
import io.pivotal.cassa.entrepreneur.*;
import io.pivotal.cassa.mediatype.DriveLink;
import io.pivotal.cassa.mediatype.DriveResource;
import io.pivotal.cassa.mediatype.DriveResourceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MonopolyController {

    public static final DriveLink RACE_CAR = DriveLink.builder()
        .href("/api/monopoly/token?token=RACE_CAR")
        .title("Race Car")
        .method(HttpMethod.POST)
        .build();

    private final EntrepreneurGenerator entrepreneurGenerator;
    private final DriveResourceGenerator resourceGenerator;

    @GetMapping("/monopoly")
    public DriveResource<Void> getMonopoly() {
        Map<String, DriveLink> links =  DriveLink.of(
            "car", RACE_CAR
        );
        return DriveResource.<Void>builder()
            .links(links)
            .build();
    }

    @PostMapping("/monopoly/token")
    public DriveResource<Monopoly> createToken(@RequestParam TokenType token) {
        Map<String, DriveLink> links =  emptyMap();
        Entrepreneur entrepreneur = entrepreneurGenerator.create(token);
        Monopoly monopoly = Monopoly.builder()
            .players(List.of(Players.builder().player1(entrepreneur).build()))
            .board(Board.builder().build())
            .build();
        return resourceGenerator.createDriveResource(links, monopoly);
    }
}
