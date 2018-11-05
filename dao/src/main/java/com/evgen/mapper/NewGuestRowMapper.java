package com.evgen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.evgen.Guest;

public class NewGuestRowMapper implements RowMapper<Guest> {

  public Guest mapRow(ResultSet resultSet, int i) throws SQLException {
    return new Guest(
        resultSet.getString("guest_id"),
        resultSet.getString("name"),
        resultSet.getString("password"),
        new ArrayList<>()
    );
  }
}
