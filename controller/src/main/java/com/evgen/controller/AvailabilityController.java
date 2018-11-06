package com.evgen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.service.AvailabilityService;

@CrossOrigin
@RestController
public class AvailabilityController {

  private final AvailabilityService availabilityService;

  @Autowired
  public AvailabilityController(AvailabilityService availabilityService) {
    this.availabilityService = availabilityService;
  }

  @GetMapping("/guests")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Guest retrieveGuestByName(@RequestParam(value = "name") String name) {
    return availabilityService.retrieveGuestByName(name);
  }

  @GetMapping("/hotels")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<Hotel> retrieveHotels(@RequestParam(value = "hotelName", required = false) String hotelName) {
    return availabilityService.retrieveHotels(hotelName);
  }

  @PostMapping("/guests")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Guest createGuest(@RequestBody Guest guest) {
    return availabilityService.createGuest(guest);
  }
}