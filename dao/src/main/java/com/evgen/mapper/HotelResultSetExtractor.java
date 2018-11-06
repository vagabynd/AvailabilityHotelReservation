package com.evgen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.evgen.Apartment;
import com.evgen.Hotel;

import javafx.util.Pair;

public class HotelResultSetExtractor implements ResultSetExtractor<List<Hotel>> {

  @Override
  public List<Hotel> extractData(ResultSet rs) throws SQLException, DataAccessException {
    List<Hotel> hotels = new ArrayList<>();
    List<Pair<String, Apartment>> apartments = new ArrayList<>();

    while (rs.next()) {
      hotels.add(new Hotel(
          rs.getString("hotel_id"),
          rs.getString("hotel_name")
      ));

      apartments.add(
          new Pair<>(
              rs.getString("hotel_id"),
              new Apartment(
                  rs.getString("apartment_id"),
                  rs.getString("room_count")
              )
          )
      );
    }

    return hotels.stream()
        .distinct()
        .peek(hotel -> hotel.setApartments(
            apartments.stream()
                .filter(a -> a.getKey().equals(hotel.getHotelId()))
                .map(Pair::getValue)
                .collect(Collectors.toList())
        ))
        .collect(Collectors.toList());
  }
}
