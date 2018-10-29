package com.evgen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.Reservation;
import com.evgen.dao.GuestRepository;
import com.evgen.dao.HotelRepository;
import com.evgen.dao.ReservationRepository;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

  private final GuestRepository guestRepository;
  private final HotelRepository hotelRepository;
  private final ReservationRepository reservationRepository;

  @Autowired
  public AvailabilityServiceImpl(GuestRepository guestRepository, HotelRepository hotelRepository,
      ReservationRepository reservationRepository) {
    this.guestRepository = guestRepository;
    this.hotelRepository = hotelRepository;
    this.reservationRepository = reservationRepository;
  }

  @Override
  public List<Reservation> retrieveReservations(String guestId) {
    return Optional.ofNullable(guestRepository.findByGuestId(guestId))
        .map(Guest::getReservations)
        .orElse(new ArrayList<>());
  }

  @Override
  public Guest retrieveGuestByName(String name) {
    return guestRepository.findByName(name);
  }

  @Override
  public List<Hotel> retrieveHotels() {
    return hotelRepository.findAll();
  }

  @Override
  public Hotel retrieveHotelByName(String hotelName) {
    return hotelRepository.findByHotelName(hotelName);
  }

  @Override
  public Guest createGuest(Guest guest) {
    return guestRepository.save(guest);
  }
}