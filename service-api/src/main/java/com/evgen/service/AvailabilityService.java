package com.evgen.service;

import java.util.List;

import com.evgen.Guest;
import org.bson.types.ObjectId;

import com.evgen.Reservation;

public interface AvailabilityService {

  List<Reservation> retrieveReservations(ObjectId guestId);

  Guest retrieveGuestByName(String name);
}
