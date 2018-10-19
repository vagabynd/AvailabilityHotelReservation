package com.evgen.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.config.DaoImplTestConfig;
import com.evgen.dao.GuestRepository;
import com.evgen.dao.HotelRepository;
import com.evgen.dao.ReservationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoImplTestConfig.class)
public class DaoImplTest {

  private static final Logger LOGGER = LogManager.getLogger(DaoImplTest.class);

  @Autowired
  GuestRepository guestRepository;

  @Autowired
  HotelRepository hotelRepository;

  @Autowired
  ReservationRepository reservationRepository;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void getGuestByNameTest() {
    LOGGER.debug("test: get guest by name");

    Guest guest = guestRepository.findByName("sergei");
    Assert.assertEquals(guest.getGuestId(), new ObjectId("5bc70e09677aa47db3942744"));
  }

  @Test
  public void getGuestByIdTest() {
    LOGGER.debug("test: get guest by name");

    Guest guest = guestRepository.findByGuestId(new ObjectId("5bc70e09677aa47db3942744"));
    Assert.assertEquals(guest.getName(), "sergei");
  }

  @Test
  public void getHotels() {
    LOGGER.debug("test: get hotels");

    List<Hotel> hotels = hotelRepository.findAll();
    Assert.assertEquals(hotels.size(), 1);
  }

  @Test
  public void getHotelByName() {
    LOGGER.debug("test: get hotels");

    Hotel hotel = hotelRepository.findByHotelName("TestHotel");
    Assert.assertNotNull(hotel);
  }
}