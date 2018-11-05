package com.evgen.service;

import java.util.List;

import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.Reservation;

public interface AvailabilityService {

  //List<Reservation> retrieveReservations(String guestId);

  Guest retrieveGuestByName(String name);

  List<Hotel> retrieveHotels(String hotelName);

  Guest createGuest(Guest guest);
}