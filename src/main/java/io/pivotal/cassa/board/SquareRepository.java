package io.pivotal.cassa.board;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SquareRepository {

    public static final SquareEntity GO = SquareEntity.builder()
        .id(1)
        .name("GO")
        .type(SquareType.GO)
        .salary(200)
        .build();

    public static final SquareEntity MED_AVE = SquareEntity.builder()
        .id(2)
        .color(ColorType.PURPLE)
        .name("Mediterranean Avenue")
        .type(SquareType.PROPERTY)
        .price(60)
        .rent(10)
        .houseRent(90)
        .hotelRent(250)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity CHEST = SquareEntity.builder()
        .id(3)
        .name("Community Chest")
        .type(SquareType.CHEST)
        .build();

    public static final SquareEntity BALTIC_AVE = SquareEntity.builder()
        .id(4)
        .color(ColorType.PURPLE)
        .name("Baltic Avenue")
        .type(SquareType.PROPERTY)
        .price(60)
        .rent(20)
        .houseRent(180)
        .hotelRent(450)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity INCOME_TAX = SquareEntity.builder()
        .id(5)
        .name("Income Tax")
        .type(SquareType.TAX)
        .tax(200)
        .build();

    public static final SquareEntity READING = SquareEntity.builder()
        .id(6)
        .name("Reading Railroad")
        .type(SquareType.RAILROAD)
        .price(200)
        .rent(200)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity ORIENTAL_AVE = SquareEntity.builder()
        .id(7)
        .name("Oriental Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(100)
        .rent(30)
        .houseRent(270)
        .hotelRent(550)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity CHANCE = SquareEntity.builder()
        .id(8)
        .name("Chance")
        .type(SquareType.CHANCE)
        .build();

    public static final SquareEntity VERMONT_AVE = SquareEntity.builder()
        .id(9)
        .name("Vermont Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(100)
        .rent(30)
        .houseRent(270)
        .hotelRent(550)
        .build();

    public static final SquareEntity CONN_AVE = SquareEntity.builder()
        .id(10)
        .name("Connecticut Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(120)
        .rent(40)
        .houseRent(300)
        .hotelRent(600)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    static final Map<Integer, SquareEntity> BOARD_MAP = new HashMap<>();
    static {
        BOARD_MAP.put(GO.getId(),GO);
        BOARD_MAP.put(MED_AVE.getId(),MED_AVE);
        BOARD_MAP.put(CHEST.getId(),CHEST);
        BOARD_MAP.put(BALTIC_AVE.getId(),BALTIC_AVE);
        BOARD_MAP.put(INCOME_TAX.getId(),INCOME_TAX);
        BOARD_MAP.put(READING.getId(),READING);
        BOARD_MAP.put(ORIENTAL_AVE.getId(),ORIENTAL_AVE);
        BOARD_MAP.put(CHANCE.getId(),CHANCE);
        BOARD_MAP.put(VERMONT_AVE.getId(),VERMONT_AVE);
        BOARD_MAP.put(CONN_AVE.getId(),CONN_AVE);
    }

    public SquareEntity findById(Integer squareId) {
        return BOARD_MAP.get(squareId);
    }
}
