package com.evgen.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.evgen.Reservation;

public interface AvailabilityService {

  List<Reservation> retrieveReservations(ObjectId guestId);
}
