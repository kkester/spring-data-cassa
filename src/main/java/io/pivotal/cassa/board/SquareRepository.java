package io.pivotal.cassa.board;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static io.pivotal.cassa.board.ColorType.*;
import static io.pivotal.cassa.board.OwnedType.*;
import static io.pivotal.cassa.board.SquareType.*;

@Component
public class SquareRepository {

    public static final SquareEntity GO = SquareEntity.builder()
        .id(1)
        .name("GO")
        .type(SquareType.GO)
        .salary(200)
        .imageName("go.jpeg")
        .build();

    public static final SquareEntity MED_AVE = SquareEntity.builder()
        .id(2)
        .color(PURPLE)
        .name("Mediterranean Avenue")
        .type(PROPERTY)
        .price(60)
        .rent(10)
        .houseRent(90)
        .hotelRent(250)
        .houseCost(150)
        .hotelCost(250)
        .ownedType(FOR_SALE)
        .imageName("medave.jpeg")
        .build();

    public static final SquareEntity CHEST = SquareEntity.builder()
        .id(3)
        .name("Community Chest")
        .type(SquareType.CHEST)
        .imageName("chest.png")
        .build();

    public static final SquareEntity BALTIC_AVE = SquareEntity.builder()
        .id(4)
        .color(PURPLE)
        .name("Baltic Avenue")
        .type(PROPERTY)
        .price(60)
        .rent(20)
        .houseRent(180)
        .hotelRent(450)
        .houseCost(150)
        .hotelCost(250)
        .ownedType(FOR_SALE)
        .imageName("baltic.png")
        .build();

    public static final SquareEntity INCOME_TAX = SquareEntity.builder()
        .id(5)
        .name("Income Tax")
        .type(TAX)
        .tax(200)
        .imageName("intax.jpeg")
        .build();

    public static final SquareEntity READING = SquareEntity.builder()
        .id(6)
        .name("Reading Railroad")
        .type(RAILROAD)
        .price(200)
        .rent(50)
        .ownedType(FOR_SALE)
        .imageName("reading.png")
        .build();

    public static final SquareEntity ORIENTAL_AVE = SquareEntity.builder()
        .id(7)
        .name("Oriental Avenue")
        .type(PROPERTY)
        .color(CYAN)
        .price(100)
        .rent(30)
        .houseRent(270)
        .hotelRent(550)
        .houseCost(150)
        .hotelCost(250)
        .ownedType(FOR_SALE)
        .imageName("oriental.jpeg")
        .build();

    public static final SquareEntity CHANCE = SquareEntity.builder()
        .id(8)
        .name("Chance")
        .type(SquareType.CHANCE)
        .imageName("chance.jpeg")
        .build();

    public static final SquareEntity VERMONT_AVE = SquareEntity.builder()
        .id(9)
        .name("Vermont Avenue")
        .type(PROPERTY)
        .color(CYAN)
        .price(100)
        .rent(30)
        .houseRent(270)
        .hotelRent(550)
        .houseCost(150)
        .hotelCost(250)
        .ownedType(FOR_SALE)
        .imageName("vermont.jpeg")
        .build();

    public static final SquareEntity CONN_AVE = SquareEntity.builder()
        .id(10)
        .name("Connecticut Avenue")
        .type(PROPERTY)
        .color(CYAN)
        .price(120)
        .rent(40)
        .houseRent(300)
        .hotelRent(600)
        .houseCost(150)
        .hotelCost(250)
        .ownedType(FOR_SALE)
        .imageName("conn.jpeg")
        .build();

    public static final SquareEntity JAIL_VISITING = SquareEntity.builder()
        .id(11)
        .name("Jail Just Visiting")
        .type(JUST_VISITING)
        .imageName("jail.jpeg")
        .build();

    public static final SquareEntity ST_CHARLES = SquareEntity.builder()
        .id(12)
        .name("St Charles Place")
        .type(PROPERTY)
        .color(PURPLE)
        .price(140)
        .rent(50)
        .houseRent(450)
        .hotelRent(750)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(FOR_SALE)
        .imageName("stcharles.jpeg")
        .build();

    public static final SquareEntity ELECTRIC_COMPANY = SquareEntity.builder()
        .id(13)
        .name("Electric Company")
        .type(UTILITY)
        .price(150)
        .rent(100)
        .ownedType(FOR_SALE)
        .imageName("electric.png")
        .build();

    public static final SquareEntity STATES_AVE = SquareEntity.builder()
        .id(14)
        .name("States Avenue")
        .type(PROPERTY)
        .color(PURPLE)
        .price(140)
        .rent(50)
        .houseRent(450)
        .hotelRent(750)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(FOR_SALE)
        .imageName("states.jpeg")
        .build();

    public static final SquareEntity VIRGINA_AVE = SquareEntity.builder()
        .id(15)
        .name("Virgina Avenue")
        .type(PROPERTY)
        .color(PURPLE)
        .price(160)
        .rent(60)
        .houseRent(500)
        .hotelRent(900)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(FOR_SALE)
        .imageName("virginia.jpeg")
        .build();

    public static final SquareEntity PENN_RAILROAD = SquareEntity.builder()
        .id(16)
        .name("Pennsylvania Railroad")
        .type(RAILROAD)
        .price(200)
        .rent(75)
        .ownedType(FOR_SALE)
        .imageName("pennrr.png")
        .build();

    public static final SquareEntity ST_JAMES = SquareEntity.builder()
        .id(17)
        .name("St James")
        .type(PROPERTY)
        .color(ORANGE)
        .price(180)
        .rent(70)
        .houseRent(550)
        .hotelRent(950)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(FOR_SALE)
        .imageName("stjames.png")
        .build();

    public static final SquareEntity CHEST2 = CHEST.toBuilder()
        .id(18)
        .build();

    public static final SquareEntity TENN_AVE = SquareEntity.builder()
        .id(19)
        .name("Tennessee Avenue")
        .type(PROPERTY)
        .color(ORANGE)
        .price(180)
        .rent(70)
        .houseRent(550)
        .hotelRent(950)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(FOR_SALE)
        .imageName("tennave.png")
        .build();

    public static final SquareEntity NY_AVE = SquareEntity.builder()
        .id(20)
        .name("New York Avenue")
        .type(PROPERTY)
        .color(ORANGE)
        .price(180)
        .rent(80)
        .houseRent(600)
        .hotelRent(1000)
        .houseCost(300)
        .hotelCost(500)
        .ownedType(FOR_SALE)
        .imageName("nyave.png")
        .build();

    public static final SquareEntity FREE_PARKING = SquareEntity.builder()
        .id(21)
        .name("Free Parking")
        .type(PARKING)
        .imageName("parking.jpeg")
        .build();

    public static final SquareEntity KENTUCKY_AVE = SquareEntity.builder()
        .id(22)
        .name("Kentucky Avenue")
        .type(PROPERTY)
        .color(RED)
        .price(220)
        .rent(90)
        .houseRent(700)
        .hotelRent(1050)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(FOR_SALE)
        .imageName("kentucky.png")
        .build();

    public static final SquareEntity CHANCE2 = CHANCE.toBuilder()
        .id(23)
        .build();

    public static final SquareEntity INDIANA_AVE = SquareEntity.builder()
        .id(24)
        .name("Indiana Avenue")
        .type(PROPERTY)
        .color(RED)
        .price(220)
        .rent(90)
        .houseRent(700)
        .hotelRent(1050)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(FOR_SALE)
        .imageName("indiana.png")
        .build();

    public static final SquareEntity ILLINOIS_AVE = SquareEntity.builder()
        .id(25)
        .name("Illinois Avenue")
        .type(PROPERTY)
        .color(RED)
        .price(240)
        .rent(100)
        .houseRent(750)
        .hotelRent(1100)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(FOR_SALE)
        .imageName("illinois.png")
        .build();

    public static final SquareEntity BO_RAILROAD = SquareEntity.builder()
        .id(26)
        .name("B.&O. Railroad")
        .type(RAILROAD)
        .price(200)
        .rent(50)
        .ownedType(FOR_SALE)
        .imageName("borr.png")
        .build();

    public static final SquareEntity ATLANTIC_AVE = SquareEntity.builder()
        .id(27)
        .name("Atlantic Avenue")
        .type(PROPERTY)
        .color(YELLOW)
        .price(260)
        .rent(110)
        .houseRent(800)
        .hotelRent(1150)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(FOR_SALE)
        .imageName("atlantic.png")
        .build();

    public static final SquareEntity VENTNOR_AVE = SquareEntity.builder()
        .id(28)
        .name("Ventnor Avenue")
        .type(PROPERTY)
        .color(YELLOW)
        .price(260)
        .rent(110)
        .houseRent(800)
        .hotelRent(1150)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(FOR_SALE)
        .imageName("ventnor.png")
        .build();

    public static final SquareEntity WATER_WORKS = SquareEntity.builder()
        .id(29)
        .name("Water Works")
        .type(UTILITY)
        .price(150)
        .rent(100)
        .ownedType(FOR_SALE)
        .imageName("water.png")
        .build();

    public static final SquareEntity MARVIN_GARDENS = SquareEntity.builder()
        .id(30)
        .name("Marvin Gardens")
        .type(PROPERTY)
        .color(YELLOW)
        .price(280)
        .rent(120)
        .houseRent(850)
        .hotelRent(1200)
        .houseCost(450)
        .hotelCost(750)
        .ownedType(FOR_SALE)
        .imageName("marvin.png")
        .build();

    public static final SquareEntity GO2JAIL = SquareEntity.builder()
        .id(31)
        .name("Go To Jail")
        .type(GOTO_JAIL)
        .imageName("go2jail.jpeg")
        .build();

    public static final SquareEntity PACIFIC_AVE = SquareEntity.builder()
        .id(32)
        .name("Pacific Ave")
        .type(PROPERTY)
        .color(GREEN)
        .price(300)
        .rent(130)
        .houseRent(900)
        .hotelRent(1250)
        .houseCost(600)
        .hotelCost(800)
        .ownedType(FOR_SALE)
        .imageName("pacific.png")
        .build();

    public static final SquareEntity NC_AVE = SquareEntity.builder()
        .id(33)
        .name("North Carolina Ave")
        .type(PROPERTY)
        .color(GREEN)
        .price(300)
        .rent(130)
        .houseRent(900)
        .hotelRent(1250)
        .houseCost(600)
        .hotelCost(800)
        .ownedType(FOR_SALE)
        .imageName("northcarolina.png")
        .build();

    public static final SquareEntity CHEST3 = CHEST.toBuilder()
        .id(34)
        .build();

    public static final SquareEntity PENN_AVE = SquareEntity.builder()
        .id(35)
        .name("Pennsylvania Ave")
        .type(PROPERTY)
        .color(GREEN)
        .price(320)
        .rent(150)
        .houseRent(1000)
        .hotelRent(1200)
        .houseCost(600)
        .hotelCost(800)
        .ownedType(FOR_SALE)
        .imageName("pennave.png")
        .build();

    public static final SquareEntity SL_RAILROAD = SquareEntity.builder()
        .id(36)
        .name("Short Line")
        .type(RAILROAD)
        .price(200)
        .rent(50)
        .ownedType(FOR_SALE)
        .imageName("slrr.png")
        .build();

    public static final SquareEntity CHANCE3 = CHANCE.toBuilder()
        .id(37)
        .build();

    public static final SquareEntity PARK_PLACE = SquareEntity.builder()
        .id(38)
        .name("Park Place")
        .type(PROPERTY)
        .color(BLUE)
        .price(350)
        .rent(175)
        .houseRent(1100)
        .hotelRent(1500)
        .houseCost(600)
        .hotelCost(800)
        .ownedType(FOR_SALE)
        .imageName("parkplace.png")
        .build();

    public static final SquareEntity LUXURY_TAX = SquareEntity.builder()
        .id(39)
        .name("Luxury Tax")
        .type(TAX)
        .tax(75)
        .imageName("luxtax.jpeg")
        .build();

    public static final SquareEntity BOARDWALK = SquareEntity.builder()
        .id(40)
        .name("Boardwalk")
        .type(PROPERTY)
        .color(BLUE)
        .price(400)
        .rent(200)
        .houseRent(1400)
        .hotelRent(2000)
        .houseCost(600)
        .hotelCost(800)
        .ownedType(FOR_SALE)
        .imageName("boardwalk.png")
        .build();

    static final Map<Integer, SquareEntity> BOARD_MAP = new HashMap<>();

    static {
        BOARD_MAP.put(GO.getId(), GO);
        BOARD_MAP.put(MED_AVE.getId(), MED_AVE);
        BOARD_MAP.put(CHEST.getId(), CHEST);
        BOARD_MAP.put(BALTIC_AVE.getId(), BALTIC_AVE);
        BOARD_MAP.put(INCOME_TAX.getId(), INCOME_TAX);
        BOARD_MAP.put(READING.getId(), READING);
        BOARD_MAP.put(ORIENTAL_AVE.getId(), ORIENTAL_AVE);
        BOARD_MAP.put(CHANCE.getId(), CHANCE);
        BOARD_MAP.put(VERMONT_AVE.getId(), VERMONT_AVE);
        BOARD_MAP.put(CONN_AVE.getId(), CONN_AVE);
        BOARD_MAP.put(JAIL_VISITING.getId(), JAIL_VISITING);
        BOARD_MAP.put(ST_CHARLES.getId(), ST_CHARLES);
        BOARD_MAP.put(ELECTRIC_COMPANY.getId(), ELECTRIC_COMPANY);
        BOARD_MAP.put(STATES_AVE.getId(), STATES_AVE);
        BOARD_MAP.put(VIRGINA_AVE.getId(), VIRGINA_AVE);
        BOARD_MAP.put(PENN_RAILROAD.getId(), PENN_RAILROAD);
        BOARD_MAP.put(ST_JAMES.getId(), ST_JAMES);
        BOARD_MAP.put(CHEST2.getId(), CHEST2);
        BOARD_MAP.put(TENN_AVE.getId(), TENN_AVE);
        BOARD_MAP.put(NY_AVE.getId(), NY_AVE);
        BOARD_MAP.put(FREE_PARKING.getId(), FREE_PARKING);
        BOARD_MAP.put(KENTUCKY_AVE.getId(), KENTUCKY_AVE);
        BOARD_MAP.put(CHANCE2.getId(), CHANCE2);
        BOARD_MAP.put(INDIANA_AVE.getId(), INDIANA_AVE);
        BOARD_MAP.put(ILLINOIS_AVE.getId(), ILLINOIS_AVE);
        BOARD_MAP.put(BO_RAILROAD.getId(), BO_RAILROAD);
        BOARD_MAP.put(ATLANTIC_AVE.getId(), ATLANTIC_AVE);
        BOARD_MAP.put(VENTNOR_AVE.getId(), VENTNOR_AVE);
        BOARD_MAP.put(WATER_WORKS.getId(), WATER_WORKS);
        BOARD_MAP.put(MARVIN_GARDENS.getId(), MARVIN_GARDENS);
        BOARD_MAP.put(GO2JAIL.getId(), GO2JAIL);
        BOARD_MAP.put(PACIFIC_AVE.getId(), PACIFIC_AVE);
        BOARD_MAP.put(NC_AVE.getId(), NC_AVE);
        BOARD_MAP.put(CHEST3.getId(), CHEST3);
        BOARD_MAP.put(PENN_AVE.getId(), PENN_AVE);
        BOARD_MAP.put(SL_RAILROAD.getId(), SL_RAILROAD);
        BOARD_MAP.put(CHANCE3.getId(), CHANCE3);
        BOARD_MAP.put(PARK_PLACE.getId(), PARK_PLACE);
        BOARD_MAP.put(LUXURY_TAX.getId(), LUXURY_TAX);
        BOARD_MAP.put(BOARDWALK.getId(), BOARDWALK);
    }

    public SquareEntity findById(Integer squareId) {
        return BOARD_MAP.get(squareId);
    }

    public Collection<SquareEntity> findAll() {
        return BOARD_MAP.values();
    }
}
