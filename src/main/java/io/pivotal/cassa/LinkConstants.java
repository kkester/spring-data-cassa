package io.pivotal.cassa;

import io.pivotal.cassa.entrepreneur.TokenType;
import io.pivotal.cassa.mediatype.DriveLink;
import org.springframework.http.HttpMethod;

import java.util.Map;

public class LinkConstants {

    public static final String TOKEN_RELATIVE_URL = "/api/monopoly/token?token=";

    public static final DriveLink RACE_CAR_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL + TokenType.RACE_CAR)
        .title("Race Car")
        .method(HttpMethod.POST)
        .build();

    public static final DriveLink BATTLESHIP_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL + TokenType.BATTLESHIP)
        .title("Battleship\n")
        .method(HttpMethod.POST)
        .build();

    public static final DriveLink BOOT_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL + TokenType.BOOT)
        .title("Boot\n")
        .method(HttpMethod.POST)
        .build();

    public static final DriveLink IRON_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL + TokenType.IRON)
        .title("Iron\n")
        .method(HttpMethod.POST)
        .build();

    public static final DriveLink CANNON_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL + TokenType.CANNON)
        .title("Cannon\n")
        .method(HttpMethod.POST)
        .build();

    public static final DriveLink THIMBLE_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL + TokenType.THIMBLE)
        .title("Thimble")
        .method(HttpMethod.POST)
        .build();

    public static final DriveLink TOP_HAT_LINK = DriveLink.builder()
        .href(TOKEN_RELATIVE_URL + TokenType.TOP_HAT)
        .title("Top Hat")
        .method(HttpMethod.POST)
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

    private LinkConstants() {
    }
}
