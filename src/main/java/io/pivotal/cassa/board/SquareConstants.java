package io.pivotal.cassa.board;

import java.util.List;

import static java.util.Arrays.asList;

public class SquareConstants {

    public static Square GO = Square.builder()
        .name("GO")
        .type(SquareType.GO)
        .salary(200)
        .build();

    public static Square MED_AVE = Square.builder()
        .color(ColorType.PURPLE)
        .name("Mediterranean Avenue")
        .type(SquareType.PROPERTY)
        .price(60)
        .build();

    public static Square CHEST = Square.builder()
        .name("Community Chest")
        .type(SquareType.CHEST)
        .build();

    public static Square BALTIC_AVE = Square.builder()
        .color(ColorType.PURPLE)
        .name("Baltic Avenue")
        .price(60)
        .build();

    public static Square INCOME_TAX = Square.builder()
        .name("Income Tax")
        .type(SquareType.TAX)
        .tax(200)
        .build();

    public static Square READING = Square.builder()
        .name("Reading Railroad")
        .type(SquareType.RAILROAD)
        .price(200)
        .build();

    public static Square ORIENTAL_AVE = Square.builder()
        .name("Oriental Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(100)
        .build();

    public static Square CHANCE = Square.builder()
        .name("Chance")
        .type(SquareType.CHANCE)
        .build();

    public static Square VERMONT_AVE = Square.builder()
        .name("Vermont Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(100)
        .build();

    public static Square CONN_AVE = Square.builder()
        .name("Connecticut Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(120)
        .build();

    public static List<Square> BOARD_SIDE1 = asList(
        GO,
        MED_AVE,
        CHEST,
        BALTIC_AVE,
        INCOME_TAX,
        READING,
        ORIENTAL_AVE,
        CHANCE,
        VERMONT_AVE,
        CONN_AVE);
}
