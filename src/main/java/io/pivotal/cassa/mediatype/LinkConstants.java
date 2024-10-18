package io.pivotal.cassa.mediatype;

import io.pivotal.cassa.board.TokenType;
import org.springframework.http.HttpMethod;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class LinkConstants {

    public static final String TOKEN_RELATIVE_URL = "/api/monopoly/token?token=%s";
    public static final String ROLL_RELATIVE_URL = "/api/monopoly/%s/roll";

    public static final DriveLink RACE_CAR_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Race Car")
        .method(HttpMethod.POST.name())
        .build()
        .applyVariables(TokenType.RACE_CAR);

    public static final DriveLink BATTLESHIP_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Battleship")
        .method(HttpMethod.POST.name())
        .build()
        .applyVariables(TokenType.BATTLESHIP);

    public static final DriveLink BOOT_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Boot")
        .method(HttpMethod.POST.name())
        .build()
        .applyVariables(TokenType.BOOT);

    public static final DriveLink IRON_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Iron")
        .method(HttpMethod.POST.name())
        .build()
        .applyVariables(TokenType.IRON);

    public static final DriveLink CANNON_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Cannon")
        .method(HttpMethod.POST.name())
        .build()
        .applyVariables(TokenType.CANNON);

    public static final DriveLink THIMBLE_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Thimble")
        .method(HttpMethod.POST.name())
        .build()
        .applyVariables(TokenType.THIMBLE);

    public static final DriveLink TOP_HAT_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Top Hat")
        .method(HttpMethod.POST.name())
        .build()
        .applyVariables(TokenType.TOP_HAT);

    public static final DriveLink ROLL_LINK = DriveLink.builder()
        .href(ROLL_RELATIVE_URL)
        .title("Roll")
        .method(HttpMethod.POST.name())
        .build();

    public static final Map<String, DriveLink> TOKEN_LINKS = DriveLink.of(
        "car", RACE_CAR_LINK,
        "boot", BOOT_LINK,
        "cannon", CANNON_LINK,
        "thimble", THIMBLE_LINK,
        "topHat", TOP_HAT_LINK,
        "iron", IRON_LINK,
        "battleship", BATTLESHIP_LINK
    );

    public static Map<String, DriveLink> rollLinks(boolean gameOver, UUID monopolyId) {
        return gameOver ?
            Collections.emptyMap() :
            DriveLink.of(
                "roll", ROLL_LINK.applyVariables(monopolyId)
            );
    }

    private LinkConstants() {
    }
}
