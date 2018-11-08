package com.evgen.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.evgen.AvailabilityDao;
import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.mapper.GuestRowMapper;
import com.evgen.mapper.HotelResultSetExtractor;
import com.evgen.mapper.NewGuestRowMapper;

@Repository
public class AvailabilityDaoImpl implements AvailabilityDao {

  private static final String GUEST_NAME = "name";
  private static final String PASSWORD = "password";
  private static final String HOTEL_NAME = "hotelName";

  @Value("${AvailabilityDaoSql.getGuestById}")
  private String getGuestByNameSql;

  @Value("${AvailabilityDaoSql.getHotelByName}")
  private String getHotelByNameSql;

  @Value("${AvailabilityDaoSql.getHotels}")
  private String getHotelsSql;

  @Value("${AvailabilityDaoSql.createGuest}")
  private String createGuestSql;

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public AvailabilityDaoImpl(
      NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Guest retrieveGuestByName(String name) {
    return namedParameterJdbcTemplate
        .queryForObject(getGuestByNameSql, new MapSqlParameterSource(GUEST_NAME, name), new GuestRowMapper());
  }

  @Override
  public List<Hotel> retrieveHotels() {
    return namedParameterJdbcTemplate.query(getHotelsSql, new HotelResultSetExtractor());
  }

  @Override
  public List<Hotel> retrieveHotelByName(String hotelName) {
    return namedParameterJdbcTemplate
        .query(getHotelByNameSql, new MapSqlParameterSource(HOTEL_NAME, hotelName), new HotelResultSetExtractor());
  }

  @Override
  public Guest createGuest(Guest guest) {
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();

    setCreateGuestParams(guest, parameterSource);

    return namedParameterJdbcTemplate.queryForObject(createGuestSql, parameterSource, new NewGuestRowMapper());
  }

  private void setCreateGuestParams(Guest guest, MapSqlParameterSource parameterSource) {
    parameterSource.addValue(GUEST_NAME, guest.getName());
    parameterSource.addValue(PASSWORD, guest.getPassword());
  }
}
