package com.evgen.service;

import java.util.List;

import com.evgen.Guest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public List<Reservation> retrieveReservations(ObjectId guestId) {
    return guestRepository.findByGuestId(guestId).getReservations();
  }

  @Override
  public Guest retrieveGuestByName(String name) {
    return guestRepository.findByName(name);
  }

}