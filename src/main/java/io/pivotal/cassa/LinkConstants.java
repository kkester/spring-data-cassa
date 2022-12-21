package io.pivotal.cassa;

import io.pivotal.cassa.entrepreneur.TokenType;
import io.pivotal.cassa.mediatype.DriveLink;
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
        .method(HttpMethod.POST)
        .build()
        .applyVariables(TokenType.RACE_CAR);

    public static final DriveLink BATTLESHIP_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Battleship\n")
        .method(HttpMethod.POST)
        .build()
        .applyVariables(TokenType.BATTLESHIP);

    public static final DriveLink BOOT_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Boot\n")
        .method(HttpMethod.POST)
        .build()
        .applyVariables(TokenType.BOOT);

    public static final DriveLink IRON_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Iron\n")
        .method(HttpMethod.POST)
        .build()
        .applyVariables(TokenType.IRON);

    public static final DriveLink CANNON_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Cannon\n")
        .method(HttpMethod.POST)
        .build()
        .applyVariables(TokenType.CANNON);

    public static final DriveLink THIMBLE_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Thimble")
        .method(HttpMethod.POST)
        .build()
        .applyVariables(TokenType.THIMBLE);

    public static final DriveLink TOP_HAT_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL)
        .title("Top Hat")
        .method(HttpMethod.POST)
        .build()
        .applyVariables(TokenType.TOP_HAT);

    public static final DriveLink ROLL_LINK = DriveLink.builder()
        .href(ROLL_RELATIVE_URL)
        .title("Roll")
        .method(HttpMethod.POST)
        .build();

    static final Map<String, DriveLink> TOKEN_LINKS = DriveLink.of(
        "car", RACE_CAR_LINK,
        "boot", BOOT_LINK,
        "cannon", CANNON_LINK,
        "thimble", THIMBLE_LINK,
        "topHat", TOP_HAT_LINK,
        "iron", IRON_LINK,
        "battleship", BATTLESHIP_LINK
    );

    public static Map<String, DriveLink> rollLinks(Monopoly monopoly) {
        return monopoly.isGameOver() ?
            Collections.emptyMap() :
            DriveLink.of(
                "roll", ROLL_LINK.applyVariables(monopoly.getId())
            );
    }

    private LinkConstants() {
    }
}
