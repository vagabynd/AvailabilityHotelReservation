package com.evgen.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.AvailabilityDao;
import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.config.DaoImplTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@Import(DaoImplTestConfig.class)
@Transactional
public class DaoImplTest {

  private static final Logger LOGGER = LogManager.getLogger(DaoImplTest.class);

  @Autowired
  AvailabilityDao availabilityDao;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void getGuestByNameTest() {
    LOGGER.debug("test: get guest by name");

    Guest guest = availabilityDao.retrieveGuestByName("sergei");
    Assert.assertEquals(guest.getGuestId(), "1");
  }

  @Test
  public void getHotels() {
    LOGGER.debug("test: get hotels");

    List<Hotel> hotels = availabilityDao.retrieveHotels();
    Assert.assertEquals(hotels.size(), 4);
  }

  @Test
  public void getHotelByName() {
    LOGGER.debug("test: get hotels");

    List<Hotel> hotels = availabilityDao.retrieveHotelByName("Abc");
    Assert.assertEquals(hotels.size(), 1);
  }
}