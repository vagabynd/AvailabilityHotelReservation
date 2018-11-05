package com.evgen;

import java.util.List;

import com.evgen.Guest;
import com.evgen.Hotel;

public interface AvailabilityDao {

  Guest retrieveGuestByName(String name);

  List<Hotel> retrieveHotels();

  Hotel retrieveHotelByName(String hotelName);

  Guest createGuest(Guest guest);

}
