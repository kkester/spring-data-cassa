package io.pivotal.cassa.board;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SquareRepository {

    public static final Square GO = Square.builder()
        .id(1)
        .name("GO")
        .type(SquareType.GO)
        .salary(200)
        .build();

    public static final Square MED_AVE = Square.builder()
        .id(2)
        .color(ColorType.PURPLE)
        .name("Mediterranean Avenue")
        .type(SquareType.PROPERTY)
        .price(60)
        .build();

    public static final Square CHEST = Square.builder()
        .id(3)
        .name("Community Chest")
        .type(SquareType.CHEST)
        .build();

    public static final Square BALTIC_AVE = Square.builder()
        .id(4)
        .color(ColorType.PURPLE)
        .name("Baltic Avenue")
        .price(60)
        .build();

    public static final Square INCOME_TAX = Square.builder()
        .id(5)
        .name("Income Tax")
        .type(SquareType.TAX)
        .tax(200)
        .build();

    public static final Square READING = Square.builder()
        .id(6)
        .name("Reading Railroad")
        .type(SquareType.RAILROAD)
        .price(200)
        .build();

    public static final Square ORIENTAL_AVE = Square.builder()
        .id(7)
        .name("Oriental Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(100)
        .build();

    public static final Square CHANCE = Square.builder()
        .id(8)
        .name("Chance")
        .type(SquareType.CHANCE)
        .build();

    public static final Square VERMONT_AVE = Square.builder()
        .id(9)
        .name("Vermont Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(100)
        .build();

    public static final Square CONN_AVE = Square.builder()
        .id(10)
        .name("Connecticut Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(120)
        .build();

    static final Map<Integer,Square> BOARD_MAP = new HashMap<>();
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

    public Square findById(Integer squareId) {
        return BOARD_MAP.get(squareId);
    }
}
