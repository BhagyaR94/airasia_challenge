package com.airasia.event_management_service.service;

import com.airasia.event_management_service.dto.TicketDTO;
import com.airasia.event_management_service.dto.TicketRequestDTO;
import com.airasia.event_management_service.entity.EventSummary;
import com.airasia.event_management_service.entity.Ticket;
import com.airasia.event_management_service.mapper.TicketMapper;
import com.airasia.event_management_service.mapper.TicketRequestMapper;
import com.airasia.event_management_service.repository.EventSummaryRepository;
import com.airasia.event_management_service.repository.TicketRepository;
import com.airasia.event_management_service.validations.TicketValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TicketingServiceImpl implements TicketingService {
    private final TicketMapper ticketMapper;
    private final TicketRequestMapper ticketRequestMapper;
    private final TicketRepository ticketRepository;
    private final EventSummaryRepository eventSummaryRepository;

    private final TicketValidator ticketValidator;

    @Override
    @Transactional
    public List<TicketDTO> bookTickets(TicketRequestDTO ticketRequestDTO) {
        List<TicketDTO> bookedTickets = new ArrayList<>();
        EventSummary evtSummary = eventSummaryRepository
                .getEventSummaryByEventId(ticketRequestDTO.getEventID())
                .orElse(null);

        ticketValidator.validate(ticketRequestDTO);

        if (Objects.nonNull(evtSummary) && (evtSummary.getBookings() + ticketRequestDTO.getNumberOfTickets()) <= evtSummary.getCapacity()) {
            for (int i = 0; i < ticketRequestDTO.getNumberOfTickets(); i++) {
                Ticket bookedTicket = ticketRepository.save(ticketRequestMapper.mapFrom(ticketRequestDTO));
                if (Objects.nonNull(bookedTicket)) {
                    evtSummary.setBookings(evtSummary.getBookings() + 1);
                    eventSummaryRepository.save(evtSummary);
                }
                bookedTickets.add(ticketMapper.mapTo(bookedTicket));
            }
        }

        return bookedTickets;
    }
}
