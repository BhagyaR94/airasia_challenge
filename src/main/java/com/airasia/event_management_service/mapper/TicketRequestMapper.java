package com.airasia.event_management_service.mapper;

import com.airasia.event_management_service.dto.TicketDTO;
import com.airasia.event_management_service.dto.TicketRequestDTO;
import com.airasia.event_management_service.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketRequestMapper {

    public Ticket mapFrom(TicketRequestDTO dto) {
        if (dto == null) return null;

        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setTicketNumber(dto.getTicketNumber());
        ticket.setEventID(dto.getEventID());
        ticket.setIssuedFor(dto.getIssuedFor());
        ticket.setIssuedBy(dto.getIssuedBy());
        ticket.setIssuedAt(dto.getIssuedAt());
        ticket.setIsValid(dto.getIsValid());

        return ticket;
    }
}
