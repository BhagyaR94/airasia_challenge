package com.airasia.event_management_service.service;

import com.airasia.event_management_service.dto.TicketDTO;
import com.airasia.event_management_service.dto.TicketRequestDTO;

import java.util.List;

public interface TicketingService {
    List<TicketDTO> bookTickets(TicketRequestDTO ticketRequestDTO);

    void rollbackTickets(List<String> ticketIds);
}
