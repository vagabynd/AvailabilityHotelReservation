package com.evgen.config;

import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evgen.dao.GuestRepository;
import com.evgen.dao.HotelRepository;
import com.evgen.dao.ReservationRepository;
import com.evgen.service.AvailabilityService;
import com.evgen.service.AvailabilityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ServiceImplTestConf {

  @Bean
  public GuestRepository guestRepository() {
    return EasyMock.createMock(GuestRepository.class);
  }

  @Bean
  public HotelRepository hotelRepository() {
    return EasyMock.createMock(HotelRepository.class);
  }

  @Bean
  public ReservationRepository reservationRepository() {
    return EasyMock.createMock(ReservationRepository.class);
  }

  @Bean
  public AvailabilityService availabilityService() {
    return new AvailabilityServiceImpl(guestRepository(), hotelRepository(), reservationRepository());
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}