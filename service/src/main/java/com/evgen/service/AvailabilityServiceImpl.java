package com.evgen.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.AvailabilityDao;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

  private final AvailabilityDao availabilityDao;

  @Autowired
  public AvailabilityServiceImpl(AvailabilityDao availabilityDao) {
    this.availabilityDao = availabilityDao;
  }

//  @Override
//  public List<Reservation> retrieveReservations(String guestId) {
//    return Optional.of(guestRepository.findByGuestId(guestId)
//        .orElseThrow(() -> new ServerWebInputException("Incorrect guestId")))
//        .map(Guest::getReservations)
//        .orElse(new ArrayList<>());
//  }

  @Override
  public Guest retrieveGuestByName(String name) {
    return availabilityDao.retrieveGuestByName(name);
  }

  @Override
  public List<Hotel> retrieveHotels(String hotelName) {
    if (!StringUtils.isEmpty(hotelName)) {
      return Collections.singletonList(availabilityDao.retrieveHotelByName(hotelName));
    }

    return availabilityDao.retrieveHotels();
  }

  @Override
  public Guest createGuest(Guest guest) {
    return availabilityDao.createGuest(guest);
  }

}