package com.evgen.messaging;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.evgen.Guest;
import com.evgen.Hotel;
import com.evgen.Reservation;
import com.evgen.service.AvailabilityService;

@Component
public class MessageReceiver {

  private static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

  private final AvailabilityService availabilityService;
  private final MessageSender messageSender;

  @Autowired
  public MessageReceiver(AvailabilityService availabilityService, MessageSender messageSender) {
    this.availabilityService = availabilityService;
    this.messageSender = messageSender;
  }

  @JmsListener(destination = "queue")
  public void receiveMessage(final Message<com.evgen.Message> message) {

    MessageHeaders headers = message.getHeaders();
    com.evgen.Message response = message.getPayload();

    switch (response.getEndPoint()) {
      case "retrieveGuestByName":
        Guest guest = availabilityService.retrieveGuestByName(response.getRequestObject().toString());
        messageSender.sendMessage(new com.evgen.Message(response.getId(), response.getEndPoint(), guest));
        break;
      case "retrieveHotels":
        List<Hotel> hotels = availabilityService.retrieveHotels(null);
        messageSender.sendMessage(new com.evgen.Message(response.getId(), response.getEndPoint(), hotels));
        break;
      case "retrieveHotelByName":
        List<Hotel> hotel = availabilityService.retrieveHotels(response.getRequestObject().toString());
        messageSender.sendMessage(new com.evgen.Message(response.getId(), response.getEndPoint(), hotel));
        break;
      case "createGuest":
        Guest guest1 = availabilityService.createGuest((Guest) response.getRequestObject());
        messageSender.sendMessage(new com.evgen.Message(response.getId(), response.getEndPoint(), guest1));
        break;
      case "retrieveReservations":
        List<Reservation> reservations = availabilityService.retrieveReservations(response.getRequestObject().toString());
        messageSender.sendMessage(new com.evgen.Message(response.getId(), response.getEndPoint(), reservations));
        break;
    }

  }
}
