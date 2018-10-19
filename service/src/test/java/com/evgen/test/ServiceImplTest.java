package com.evgen.test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

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

import com.evgen.Guest;
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

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private AvailabilityService reservationService;

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

    List<Reservation> reservations = reservationService.retrieveReservations(new ObjectId("5bc449c09ddbcd660ac58f07"));

    Assert.assertEquals(reservations.size(), 1);
  }
}