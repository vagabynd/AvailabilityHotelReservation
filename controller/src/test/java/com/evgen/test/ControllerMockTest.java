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

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.evgen.Guest;
import com.evgen.Reservation;
import com.evgen.config.ControllerMockTestConf;
import com.evgen.service.AvailabilityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ControllerMockTestConf.class)
@WebAppConfiguration
public class ControllerMockTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Autowired
  private AvailabilityService availabilityService;

  @After
  public void tearDown() {
    verify(availabilityService);
    reset(availabilityService);
  }

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void retrieveReservationsTest() throws Exception {
    expect(availabilityService.retrieveReservations(new ObjectId("5bc7340b677aa44e986d19db")))
        .andReturn(new ArrayList<Reservation>());
    replay(availabilityService);

    mockMvc.perform(
        get("/reservations")
            .accept(MediaType.APPLICATION_JSON)
            .header("guestId", "5bc7340b677aa44e986d19db")
    ).andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void retrieveGuestByNameTest() throws Exception {
    expect(availabilityService.retrieveGuestByName(anyString()))
        .andReturn(new Guest());
    replay(availabilityService);

    mockMvc.perform(
        get("/guests/sergei")
            .accept(MediaType.APPLICATION_JSON)
    ).andDo(print())
        .andExpect(status().isOk());
  }
}