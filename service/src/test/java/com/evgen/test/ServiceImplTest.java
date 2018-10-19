package com.evgen.test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.evgen.Apartment;
import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.Reservation;
import com.evgen.dao.GuestRepository;
import com.evgen.dao.HotelRepository;
import com.evgen.dao.ReservationRepository;
import com.evgen.service.AvailabilityService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.evgen.config.ServiceImplTestConf;

@RunWith(SpringJUnit4ClassRunner.class)
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
  private GuestRepository guestRepositoryMock;

  @Autowired
  private HotelRepository hotelRepositoryMock;

  @Autowired
  private ReservationRepository reservationRepositoryMock;

  @After
  public void clean() {
    verify();
  }

  @Before
  public void setUp() {
    reset(guestRepositoryMock);
    reset(reservationRepositoryMock);
    reset(hotelRepositoryMock);
  }

  @Test
  public void retrieveReservationsTest() throws Exception {
    LOGGER.debug("test: retrieve reservations");

    Guest guest = objectMapper.readValue(getClass().getResourceAsStream(GUEST), Guest.class);
    expect(guestRepositoryMock.findByGuestId(new ObjectId("5bc449c09ddbcd660ac58f07"))).andReturn(guest);
    replay(guestRepositoryMock);

    List<Reservation> reservations = availabilityService.retrieveReservations(new ObjectId("5bc449c09ddbcd660ac58f07"));

    Assert.assertEquals(reservations.size(), 1);
  }

  @Test
  public void retrieveGuestByNameTest() throws Exception {
    LOGGER.debug("test: retrieve guest by name");

    Guest guest = objectMapper.readValue(getClass().getResourceAsStream(GUEST), Guest.class);
    expect(guestRepositoryMock.findByName("sergei")).andReturn(guest);
    replay(guestRepositoryMock);

    Guest guestReturn = availabilityService.retrieveGuestByName("sergei");

    Assert.assertEquals(guestReturn.getGuestId(), guest.getGuestId());
  }

  @Test
  public void retrieveHotels() throws Exception {
    LOGGER.debug("test: retrieve hotel by name");

    Hotel hotel = objectMapper.readValue(getClass().getResourceAsStream(HOTEL), Hotel.class);
    List<Hotel> hotels = new ArrayList<>();
    hotels.add(hotel);

    expect(hotelRepositoryMock.findAll()).andReturn(hotels);
    replay(hotelRepositoryMock);

    List<Hotel> hotelsTest = availabilityService.retrieveHotels();
    Assert.assertEquals(hotelsTest.size(), 1);
  }

  @Test
  public void retrieveApartmentsByHotelNameTest() throws Exception {
    LOGGER.debug("test: retrieve apartments by hotel name");

    Hotel hotel = objectMapper.readValue(getClass().getResourceAsStream(HOTEL), Hotel.class);

    expect(hotelRepositoryMock.findByHotelName(hotel.getHotelName())).andReturn(hotel);
    replay(hotelRepositoryMock);

    Hotel hotelReturn = availabilityService.retrieveHotelByName(hotel.getHotelName());
    Assert.assertEquals(hotelReturn.getHotelId(), hotel.getHotelId());
  }
}