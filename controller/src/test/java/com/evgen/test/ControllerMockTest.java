package com.evgen.test;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.evgen.Guest;
import com.evgen.config.ControllerMockTestConf;
import com.evgen.controller.AvailabilityController;
import com.evgen.service.AvailabilityService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ControllerMockTestConf.class)
@WebMvcTest(AvailabilityController.class)
@AutoConfigureMockMvc
public class ControllerMockTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AvailabilityService availabilityService;

  @After
  public void tearDown() {
    verify(availabilityService);
    reset(availabilityService);
  }

  @Test
  public void retrieveGuestByNameTest() throws Exception {
    expect(availabilityService.retrieveGuestByName(anyString()))
        .andReturn(new Guest());
    replay(availabilityService);

    mockMvc.perform(
        get("/guests?name=sergei")
            .accept(MediaType.APPLICATION_JSON)
    ).andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void retrieveHotelsTest() throws Exception {
    expect(availabilityService.retrieveHotels(null)).andReturn(new ArrayList<>());
    replay(availabilityService);

    mockMvc.perform(
        get("/hotels")
            .accept(MediaType.APPLICATION_JSON)
    ).andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void retrieveHotelByNameTest() throws Exception {
    expect(availabilityService.retrieveHotels(anyString())).andReturn(new ArrayList<>());
    replay(availabilityService);

    mockMvc.perform(
        get("/hotels?hotelName=name")
            .accept(MediaType.APPLICATION_JSON)
    ).andDo(print())
        .andExpect(status().isOk());
  }
}