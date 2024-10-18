package io.pivotal.cassa.monopoly.web;

import io.pivotal.cassa.board.TokenType;
import io.pivotal.cassa.monopoly.executors.MonopolyExecutor;
import io.pivotal.cassa.mediatype.DriveResource;
import io.pivotal.cassa.mediatype.DriveResourceGenerator;
import io.pivotal.cassa.monopoly.components.MonopolyGenerator;
import io.pivotal.cassa.monopoly.components.MonopolyRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static io.pivotal.cassa.mediatype.LinkConstants.TOKEN_LINKS;
import static io.pivotal.cassa.mediatype.LinkConstants.rollLinks;
import static java.util.Collections.emptyMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class MonopolyController {

    private final MonopolyGenerator monopolyGenerator;
    private final MonopolyRetriever monopolyRetriever;
    private final MonopolyExecutor monopolyExecutor;
    private final DriveResourceGenerator resourceGenerator;

    @GetMapping("/monopoly")
    public DriveResource<Void> getMonopoly() {
        return DriveResource.<Void>builder()
            .links(TOKEN_LINKS)
            .build();
    }

    @GetMapping("/monopoly/{monopolyId}")
    public DriveResource<MonopolyDto> getMonopolyById(@PathVariable UUID monopolyId) {
        MonopolyDto monopoly = monopolyRetriever.get(monopolyId);
        return resourceGenerator.createDriveResource(rollLinks(monopoly.isGameOver(), monopoly.getId()), monopoly);
    }

    @PostMapping("/monopoly/token")
    public DriveResource<MonopolyDto> createToken(@RequestParam TokenType token) {
        MonopolyDto monopoly = monopolyGenerator.create(token);
        return resourceGenerator.createDriveResource(emptyMap(), monopoly);
    }

    @PostMapping("/monopoly/{monopolyId}/roll")
    public DriveResource<MonopolyDto> rollDice(@PathVariable UUID monopolyId) {
        monopolyExecutor.rollDice(monopolyId);
        MonopolyDto monopoly = monopolyRetriever.get(monopolyId);
        return resourceGenerator.createDriveResource(rollLinks(monopoly.isGameOver(), monopoly.getId()), monopoly);
    }
}
