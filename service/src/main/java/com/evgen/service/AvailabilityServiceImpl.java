package com.evgen.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebInputException;

import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.Reservation;
import com.evgen.dao.GuestRepository;
import com.evgen.dao.HotelRepository;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

  private final GuestRepository guestRepository;
  private final HotelRepository hotelRepository;

  @Autowired
  public AvailabilityServiceImpl(GuestRepository guestRepository, HotelRepository hotelRepository) {
    this.guestRepository = guestRepository;
    this.hotelRepository = hotelRepository;
  }

  @Override
  public List<Reservation> retrieveReservations(String guestId) {
    return Optional.of(guestRepository.findByGuestId(guestId)
        .orElseThrow(() -> new ServerWebInputException("Incorrect guestId")))
        .map(Guest::getReservations)
        .orElse(new ArrayList<>());
  }

  @Override
  public Guest retrieveGuestByName(String name) {
    return guestRepository.findByName(name).orElseThrow(() -> new ServerWebInputException("Incorrect name"));
  }

  @Override
  public List<Hotel> retrieveHotels(String hotelName) {
    if (!StringUtils.isEmpty(hotelName)) {
      return Collections.singletonList(retrieveHotelByName(hotelName));
    }

    return hotelRepository.findAll();
  }

  private Hotel retrieveHotelByName(String hotelName) {
    return hotelRepository.findByHotelName(hotelName)
        .orElseThrow(() -> new ServerWebInputException("Incorrect hotelName"));
  }

  @Override
  public Guest createGuest(Guest guest) {
    return guestRepository.save(guest);
  }
}