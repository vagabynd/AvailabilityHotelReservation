package com.evgen.service;

import java.util.List;

import com.evgen.Guest;
import com.evgen.Hotel;

public interface AvailabilityService {

  Guest retrieveGuestByName(String name);

  List<Hotel> retrieveHotels(String hotelName);

  Guest createGuest(Guest guest);

}