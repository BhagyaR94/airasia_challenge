package com.airasia.event_management_service.mapper;

import com.airasia.event_management_service.dto.TicketDTO;
import com.airasia.event_management_service.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDTO mapTo(Ticket ticket) {
        if (ticket == null) return null;

        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setTicketNumber(ticket.getTicketNumber());
        dto.setEventID(ticket.getEventID());
        dto.setIssuedFor(ticket.getIssuedFor());
        dto.setIssuedBy(ticket.getIssuedBy());
        dto.setIssuedAt(ticket.getIssuedAt());
        dto.setIsValid(ticket.getIsValid());

        return dto;
    }

    public Ticket mapFrom(TicketDTO dto) {
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
