package com.airasia.event_management_service.controller;

import com.airasia.event_management_service.dto.TicketDTO;
import com.airasia.event_management_service.dto.TicketRequestDTO;
import com.airasia.event_management_service.service.TicketingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketingServiceImpl ticketingService;

    @PostMapping("/book")
    public ResponseEntity<List<TicketDTO>> bookTicket(@RequestBody final TicketRequestDTO ticketDTO) {
        return ResponseEntity.ok(ticketingService.bookTickets(ticketDTO));
    }
}
