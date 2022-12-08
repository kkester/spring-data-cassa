package io.pivotal.cassa.monopoly;

import io.pivotal.cassa.mediatype.DriveLink;
import io.pivotal.cassa.mediatype.DriveResource;
import io.pivotal.cassa.mediatype.DriveResourceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MonopolyController {

    public static final DriveLink START = DriveLink.builder()
        .href("/api/start")
        .title("Start")
        .build();

    private final DriveResourceGenerator resourceGenerator;

    @GetMapping("/monopoly")
    public DriveResource<Void> getMonopoly() {
        Map<String, DriveLink> links =  DriveLink.of(
            "start", START
        );
        return DriveResource.<Void>builder()
            .links(links)
            .build();
    }
}
