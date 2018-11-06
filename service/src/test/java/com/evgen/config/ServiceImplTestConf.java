package com.evgen.config;

import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evgen.AvailabilityDao;
import com.evgen.dao.AvailabilityDaoImpl;
import com.evgen.service.AvailabilityService;
import com.evgen.service.AvailabilityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ServiceImplTestConf {

  @Bean
  public AvailabilityDao availabilityDao() {
    return EasyMock.createMock(AvailabilityDaoImpl.class);
  }

  @Bean
  public AvailabilityService availabilityService() {
    return new AvailabilityServiceImpl(availabilityDao());
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}