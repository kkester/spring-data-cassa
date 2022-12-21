package io.pivotal.cassa;

import io.pivotal.cassa.entrepreneur.TokenType;
import io.pivotal.cassa.executors.MonopolyExecutor;
import io.pivotal.cassa.mediatype.DriveResource;
import io.pivotal.cassa.mediatype.DriveResourceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static io.pivotal.cassa.LinkConstants.TOKEN_LINKS;
import static io.pivotal.cassa.LinkConstants.rollLinks;
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
    public DriveResource<Monopoly> getMonopolyById(@PathVariable UUID monopolyId) {
        Monopoly monopoly = monopolyRetriever.get(monopolyId);
        return resourceGenerator.createDriveResource(rollLinks(monopoly), monopoly);
    }

    @PostMapping("/monopoly/token")
    public DriveResource<Monopoly> createToken(@RequestParam TokenType token) {
        Monopoly monopoly = monopolyGenerator.create(token);
        return resourceGenerator.createDriveResource(emptyMap(), monopoly);
    }

    @PostMapping("/monopoly/{monopolyId}/roll")
    public DriveResource<Monopoly> rollDice(@PathVariable UUID monopolyId) {
        monopolyExecutor.rollDice(monopolyId);
        Monopoly monopoly = monopolyRetriever.get(monopolyId);
        return resourceGenerator.createDriveResource(rollLinks(monopoly), monopoly);
    }
}
