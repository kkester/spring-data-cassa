package io.pivotal.cassa.board;

import org.springframework.stereotype.Component;

import java.util.Collection;
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
        .houseCost(150)
        .hotelCost(250)
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
        .houseCost(150)
        .hotelCost(250)
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
        .houseCost(150)
        .hotelCost(250)
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
        .houseCost(150)
        .hotelCost(250)
        .ownedType(OwnedType.FOR_SALE)
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
        .houseCost(150)
        .hotelCost(250)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity JAIL_VISITING = SquareEntity.builder()
        .id(11)
        .name("Jail Just Visiting")
        .type(SquareType.JUST_VISITING)
        .build();

    public static final SquareEntity ST_CHARLES = SquareEntity.builder()
        .id(12)
        .name("St Charles Place")
        .type(SquareType.PROPERTY)
        .color(ColorType.PURPLE)
        .price(140)
        .rent(50)
        .houseRent(450)
        .hotelRent(750)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity ELECTRIC_COMPANY = SquareEntity.builder()
        .id(13)
        .name("Electric Company")
        .type(SquareType.UTILITY)
        .price(150)
        .rent(150)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity STATES_AVE = SquareEntity.builder()
        .id(14)
        .name("States Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.PURPLE)
        .price(140)
        .rent(50)
        .houseRent(450)
        .hotelRent(750)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity VIRGINA_AVE = SquareEntity.builder()
        .id(15)
        .name("Virgina Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.PURPLE)
        .price(160)
        .rent(60)
        .houseRent(500)
        .hotelRent(900)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity PENN_RAILROAD = SquareEntity.builder()
        .id(16)
        .name("Pennsylvania Railroad")
        .type(SquareType.RAILROAD)
        .price(200)
        .rent(200)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity ST_JAMES = SquareEntity.builder()
        .id(17)
        .name("St James")
        .type(SquareType.PROPERTY)
        .color(ColorType.ORANGE)
        .price(180)
        .rent(70)
        .houseRent(550)
        .hotelRent(950)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity CHEST2 = CHEST.toBuilder()
        .id(18)
        .build();

    public static final SquareEntity TENN_AVE = SquareEntity.builder()
        .id(19)
        .name("Tennessee Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.ORANGE)
        .price(180)
        .rent(70)
        .houseRent(550)
        .hotelRent(950)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity NY_AVE = SquareEntity.builder()
        .id(20)
        .name("New York Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.ORANGE)
        .price(180)
        .rent(80)
        .houseRent(600)
        .hotelRent(1000)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity FREE_PARKING = SquareEntity.builder()
        .id(21)
        .name("Free Parking")
        .type(SquareType.PARKING)
        .build();

    public static final SquareEntity KENTUCKY_AVE = SquareEntity.builder()
        .id(22)
        .name("Kentucky Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.RED)
        .price(220)
        .rent(90)
        .houseRent(700)
        .hotelRent(1050)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity CHANCE2 = CHANCE.toBuilder()
        .id(23)
        .build();

    public static final SquareEntity INDIANA_AVE = SquareEntity.builder()
        .id(24)
        .name("Indiana Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.RED)
        .price(220)
        .rent(90)
        .houseRent(700)
        .hotelRent(1050)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity ILLINOIS_AVE = SquareEntity.builder()
        .id(25)
        .name("Illinois Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.RED)
        .price(240)
        .rent(100)
        .houseRent(750)
        .hotelRent(1100)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity BO_RAILROAD = SquareEntity.builder()
        .id(26)
        .name("B.&O. Railroad")
        .type(SquareType.RAILROAD)
        .price(200)
        .rent(200)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity ATLANTIC_AVE = SquareEntity.builder()
        .id(27)
        .name("Atlantic Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.YELLOW)
        .price(260)
        .rent(110)
        .houseRent(800)
        .hotelRent(1150)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity VENTNOR_AVE = SquareEntity.builder()
        .id(28)
        .name("Ventnor Avenue")
        .type(SquareType.PROPERTY)
        .color(ColorType.YELLOW)
        .price(260)
        .rent(110)
        .houseRent(800)
        .hotelRent(1150)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity WATER_WORKS = SquareEntity.builder()
        .id(29)
        .name("Water Works")
        .type(SquareType.UTILITY)
        .price(150)
        .rent(150)
        .ownedType(OwnedType.FOR_SALE)
        .build();

    public static final SquareEntity MARVIN_GARDENS = SquareEntity.builder()
        .id(30)
        .name("Marvin Gardens")
        .type(SquareType.PROPERTY)
        .color(ColorType.YELLOW)
        .price(280)
        .rent(120)
        .houseRent(850)
        .hotelRent(1200)
        .houseCost(450)
        .hotelCost(750)
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
        BOARD_MAP.put(JAIL_VISITING.getId(),JAIL_VISITING);
        BOARD_MAP.put(ST_CHARLES.getId(),ST_CHARLES);
        BOARD_MAP.put(ELECTRIC_COMPANY.getId(),ELECTRIC_COMPANY);
        BOARD_MAP.put(STATES_AVE.getId(),STATES_AVE);
        BOARD_MAP.put(VIRGINA_AVE.getId(),VIRGINA_AVE);
        BOARD_MAP.put(PENN_RAILROAD.getId(),PENN_RAILROAD);
        BOARD_MAP.put(ST_JAMES.getId(),ST_JAMES);
        BOARD_MAP.put(CHEST2.getId(),CHEST2);
        BOARD_MAP.put(TENN_AVE.getId(),TENN_AVE);
        BOARD_MAP.put(NY_AVE.getId(),NY_AVE);
        BOARD_MAP.put(FREE_PARKING.getId(),FREE_PARKING);
        BOARD_MAP.put(KENTUCKY_AVE.getId(),KENTUCKY_AVE);
        BOARD_MAP.put(CHANCE2.getId(),CHANCE2);
        BOARD_MAP.put(INDIANA_AVE.getId(),INDIANA_AVE);
        BOARD_MAP.put(ILLINOIS_AVE.getId(),ILLINOIS_AVE);
        BOARD_MAP.put(BO_RAILROAD.getId(),BO_RAILROAD);
        BOARD_MAP.put(ATLANTIC_AVE.getId(),ATLANTIC_AVE);
        BOARD_MAP.put(VENTNOR_AVE.getId(),VENTNOR_AVE);
        BOARD_MAP.put(WATER_WORKS.getId(),WATER_WORKS);
        BOARD_MAP.put(MARVIN_GARDENS.getId(),MARVIN_GARDENS);
    }

    public SquareEntity findById(Integer squareId) {
        return BOARD_MAP.get(squareId);
    }

    public Collection<SquareEntity> findAll() {
        return BOARD_MAP.values();
    }
}
