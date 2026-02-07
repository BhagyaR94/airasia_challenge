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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketingServiceImplTest {

    @Mock
    private TicketMapper ticketMapper;

    @Mock
    private TicketRequestMapper ticketRequestMapper;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private EventSummaryRepository eventSummaryRepository;

    @Mock
    private TicketValidator ticketValidator;

    @InjectMocks
    private TicketingServiceImpl ticketingService;

    @Test
    void testBookTicketsSuccessfully() {
        TicketRequestDTO requestDTO = new TicketRequestDTO();
        requestDTO.setEventID("EVT-1");
        requestDTO.setNumberOfTickets(2);

        EventSummary summary = new EventSummary();
        summary.setEventId("EVT-1");
        summary.setCapacity(10);
        summary.setBookings(5);

        Ticket ticket = new Ticket();
        ticket.setEventID("EVT-1");

        TicketDTO ticketDTO = new TicketDTO();

        when(eventSummaryRepository.getEventSummaryByEventId("EVT-1"))
                .thenReturn(Optional.of(summary));
        when(ticketRequestMapper.mapFrom(requestDTO)).thenReturn(ticket);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        when(ticketMapper.mapTo(ticket)).thenReturn(ticketDTO);

        List<TicketDTO> result = ticketingService.bookTickets(requestDTO);

        assertEquals(2, result.size());
        verify(ticketValidator).validate(requestDTO);
        verify(ticketRepository, times(2)).save(any(Ticket.class));
        verify(eventSummaryRepository, times(2)).save(summary);
    }

    @Test
    void testNotBookTicketsWhenCapacityExceeded() {
        TicketRequestDTO requestDTO = new TicketRequestDTO();
        requestDTO.setEventID("EVT-1");
        requestDTO.setNumberOfTickets(5);

        EventSummary summary = new EventSummary();
        summary.setCapacity(6);
        summary.setBookings(5);

        when(eventSummaryRepository.getEventSummaryByEventId("EVT-1"))
                .thenReturn(Optional.of(summary));

        List<TicketDTO> result = ticketingService.bookTickets(requestDTO);

        assertTrue(result.isEmpty());
        verify(ticketRepository, never()).save(any());
    }

    @Test
    void testThrowExceptionWhenValidationFails() {
        TicketRequestDTO requestDTO = new TicketRequestDTO();

        doThrow(new IllegalArgumentException("Invalid request"))
                .when(ticketValidator).validate(requestDTO);

        assertThrows(IllegalArgumentException.class,
                () -> ticketingService.bookTickets(requestDTO));

        verify(ticketRepository, never()).save(any());
    }

    @Test
    void testRollbackTicketsSuccessfully() {
        List<String> ticketIds = List.of("T1", "T2");

        Ticket t1 = new Ticket();
        t1.setEventID("EVT-1");

        Ticket t2 = new Ticket();
        t2.setEventID("EVT-1");

        EventSummary summary = new EventSummary();
        summary.setBookings(5);

        when(ticketRepository.findTickets(ticketIds))
                .thenReturn(Optional.of(List.of(t1, t2)));
        when(eventSummaryRepository.getEventSummaryByEventId("EVT-1"))
                .thenReturn(Optional.of(summary));

        ticketingService.rollbackTickets(ticketIds);

        verify(ticketRepository).deleteAll(List.of(t1, t2));
        verify(eventSummaryRepository).save(summary);
        assertEquals(3, summary.getBookings());
    }

    @Test
    void testThrowExceptionWhenSomeTicketsNotFound() {
        List<String> ticketIds = List.of("T1", "T2");

        when(ticketRepository.findTickets(ticketIds))
                .thenReturn(Optional.of(List.of(new Ticket())));

        assertThrows(IllegalStateException.class,
                () -> ticketingService.rollbackTickets(ticketIds));
    }

    @Test
    void testReturnSilentlyWhenTicketListIsEmpty() {
        ticketingService.rollbackTickets(List.of());

        verifyNoInteractions(ticketRepository);
        verifyNoInteractions(eventSummaryRepository);
    }
}

