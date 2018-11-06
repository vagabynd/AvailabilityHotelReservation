package com.evgen.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.evgen.Apartment;
import com.evgen.Hotel;

public class ApartmentBuilder {

  public static Hotel buildApartment(Hotel hotel, ResultSet resultSet) throws SQLException {
    do {
      if (hotel.getHotelId().equals(resultSet.getString("hotel_id"))) {
        hotel.getApartments().add(
            new Apartment(
                resultSet.getString("apartment_id"),
                resultSet.getString("room_count"))
        );
      }
    } while (resultSet.next());
    return hotel;
  }
}
