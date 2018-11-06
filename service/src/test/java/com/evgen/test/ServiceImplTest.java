package com.evgen.test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.evgen.AvailabilityDao;
import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.config.ServiceImplTestConf;
import com.evgen.service.AvailabilityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceImplTestConf.class)
public class ServiceImplTest {

  private static final Logger LOGGER = LogManager.getLogger(ServiceImplTest.class);

  private static final String GUEST = "/Guest-with-reservations.json";
  private static final String HOTEL = "/Hotel.json";

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private AvailabilityService availabilityService;

 @Autowired
 private AvailabilityDao availabilityDao;

  @After
  public void clean() {
    verify();
  }

  @Before
  public void setUp() {
    reset(availabilityDao);
  }

  @Test
  public void retrieveGuestByNameTest() throws Exception {
    LOGGER.debug("test: retrieve guest by name");

    Guest guest = objectMapper.readValue(getClass().getResourceAsStream(GUEST), Guest.class);
    expect(availabilityDao.retrieveGuestByName("sergei")).andReturn(guest);
    replay(availabilityDao);

    Guest guestReturn = availabilityService.retrieveGuestByName("sergei");

    Assert.assertEquals(guestReturn.getGuestId(), guest.getGuestId());
  }

  @Test
  public void retrieveHotelsTest() throws Exception {
    LOGGER.debug("test: retrieve hotel by name");

    Hotel hotel = objectMapper.readValue(getClass().getResourceAsStream(HOTEL), Hotel.class);
    List<Hotel> hotels = new ArrayList<>();
    hotels.add(hotel);

    expect(availabilityDao.retrieveHotels()).andReturn(hotels);
    replay(availabilityDao);

    List<Hotel> hotelsTest = availabilityService.retrieveHotels(null);
    Assert.assertEquals(hotelsTest.size(), 1);
  }

  @Test
  public void retrieveHotelByName() throws Exception {
    LOGGER.debug("test: retrieve apartments by hotel name");

    Hotel hotel = objectMapper.readValue(getClass().getResourceAsStream(HOTEL), Hotel.class);

    expect(availabilityDao.retrieveHotelByName(hotel.getHotelName())).andReturn(Collections.singletonList(hotel));
    replay(availabilityDao);

    List<Hotel> hotelReturn = availabilityService.retrieveHotels(hotel.getHotelName());
    Assert.assertEquals(hotelReturn.get(0).getHotelId(), hotel.getHotelId());
  }
}