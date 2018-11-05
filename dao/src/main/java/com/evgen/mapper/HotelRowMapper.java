package com.evgen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.evgen.Apartment;
import com.evgen.Hotel;

public class HotelRowMapper implements RowMapper<Hotel> {

  public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
      Hotel hotel = new Hotel(
          resultSet.getString("hotel_id"),
          resultSet.getString("hotel_name"),
          new ArrayList<>()
      );

      if (resultSet.getObject("apartment_id", Integer.class) == null) {
        return hotel;
      }

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
