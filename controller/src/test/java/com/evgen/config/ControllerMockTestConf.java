package com.evgen.config;

import org.easymock.EasyMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.evgen.service.AvailabilityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestConfiguration
@EnableWebMvc
@ComponentScan(basePackages = "com.evgen.controller")
public class ControllerMockTestConf {

  @Bean
  public AvailabilityService availabilityService() {
    return EasyMock.createMock(AvailabilityService.class);
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}