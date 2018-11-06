package com.evgen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.evgen.Hotel;
import com.evgen.builder.ApartmentBuilder;

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

      ApartmentBuilder.buildApartment(hotel, resultSet);

      return hotel;
  }
}
