package com.evgen;

import java.util.List;

public interface AvailabilityDao {

  Guest retrieveGuestByName(String name);

  List<Hotel> retrieveHotels();

  List<Hotel> retrieveHotelByName(String hotelName);

  Guest createGuest(Guest guest);

}
