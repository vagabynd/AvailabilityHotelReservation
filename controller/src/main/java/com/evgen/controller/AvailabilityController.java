package com.evgen.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.evgen.Guest;
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
