package io.pivotal.cassa.board;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class SquareRepository {

    public static Square GO = Square.builder()
        .id(0)
        .name("GO")
        .type(SquareType.GO)
        .salary(200)
        .build();

    public static Square MED_AVE = Square.builder()
        .id(1)
        .color(ColorType.PURPLE)
        .name("Mediterranean Avenue")
        .type(SquareType.PROPERTY)
        .price(60)
        .build();

    public static Square CHEST = Square.builder()
        .id(3)
        .name("Community Chest")
        .type(SquareType.CHEST)
        .build();

    public static Square BALTIC_AVE = Square.builder()
        .id(4)
        .color(ColorType.PURPLE)
        .name("Baltic Avenue")
        .price(60)
        .build();

    public static Square INCOME_TAX = Square.builder()
        .id(5)
        .name("Income Tax")
        .type(SquareType.TAX)
        .tax(200)
        .build();

    public static Square READING = Square.builder()
        .id(6)
        .name("Reading Railroad")
        .type(SquareType.RAILROAD)
        .price(200)
        .build();

    public static Square ORIENTAL_AVE = Square.builder()
        .id(7)
        .name("Oriental Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(100)
        .build();

    public static Square CHANCE = Square.builder()
        .id(8)
        .name("Chance")
        .type(SquareType.CHANCE)
        .build();

    public static Square VERMONT_AVE = Square.builder()
        .id(9)
        .name("Vermont Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.CYAN)
        .price(100)
        .build();

    public static Square CONN_AVE = Square.builder()
        .id(10)
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

    public Square findById(Integer squareName) {
        return GO;
    }
}
