package com.evgen.controller;

import java.util.List;

import com.evgen.Apartment;
import com.evgen.Guest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.evgen.Hotel;
import com.evgen.Reservation;
import com.evgen.service.AvailabilityService;

@CrossOrigin
@RestController
public class AvailabilityController {

  private final AvailabilityService availabilityService;

  @Autowired
  public AvailabilityController(AvailabilityService availabilityService) {
    this.availabilityService = availabilityService;
  }

  @GetMapping("/reservations")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<Reservation> retrieveReservations(@RequestHeader ObjectId guestId) {
    return availabilityService.retrieveReservations(guestId);
  }

  @GetMapping("/guests/{name}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Guest retrieveGuestByName(@PathVariable("name") String name) {
    return availabilityService.retrieveGuestByName(name);
  }

  @GetMapping("/hotels")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<Hotel> retrieveHotels() {
    return availabilityService.retrieveHotels();
  }

  @GetMapping("/hotels/{name}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Hotel retrieveHotelByName(@PathVariable("name") String hotelName) {
    return availabilityService.retrieveHotelByName(hotelName);
  }
}
