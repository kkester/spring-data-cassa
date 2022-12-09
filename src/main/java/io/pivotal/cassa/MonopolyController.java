package io.pivotal.cassa;

import io.pivotal.cassa.entrepreneur.*;
import io.pivotal.cassa.mediatype.DriveResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static io.pivotal.cassa.LinkConstants.TOKEN_LINKS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MonopolyController {

    private final MonopolyResourceGenerator monopolyResourceGenerator;

    @GetMapping("/monopoly")
    public DriveResource<Void> getMonopoly() {
        return DriveResource.<Void>builder()
            .links(TOKEN_LINKS)
            .build();
    }

    @PostMapping("/monopoly/token")
    public DriveResource<Monopoly> createToken(@RequestParam TokenType token) {
        return monopolyResourceGenerator.create(token);
    }
}
