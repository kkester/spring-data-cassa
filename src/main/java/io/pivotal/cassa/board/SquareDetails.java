package io.pivotal.cassa.board;

public interface SquareDetails {
    Integer getId();
    Integer getHotelCost();
    Integer getHouseCost();
    Integer getPrice();
    SquareType getType();
    Integer getRent();
    Integer getHouseRent();
    Integer getHotelRent();
    Integer getTax();
    String getImageName();
}
