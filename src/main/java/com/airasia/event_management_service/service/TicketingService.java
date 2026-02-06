package com.airasia.event_management_service.service;

import com.airasia.event_management_service.dto.TicketDTO;
import com.airasia.event_management_service.dto.TicketRequestDTO;
import com.airasia.event_management_service.entity.Ticket;

import java.util.List;

public interface TicketingService {
    List<TicketDTO> bookTickets(TicketRequestDTO ticketRequestDTO);
}
