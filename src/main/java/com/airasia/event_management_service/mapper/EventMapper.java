package com.airasia.event_management_service.mapper;

import com.airasia.event_management_service.dto.EventDTO;
import com.airasia.event_management_service.entity.Event;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EventMapper {

    public EventDTO mapTo(Event event) {
        if (event == null) return null;

        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setEventTitle(event.getEventTitle());
        dto.setEventOrganizedBy(event.getEventOrganizedBy());
        dto.setEventTime(event.getEventTime());
        dto.setEventLocation(event.getEventLocation());
        dto.setEventTicketPrices(event.getEventTicketPrices() != null ? List.copyOf(event.getEventTicketPrices()) : List.of());
        dto.setEventTypes(event.getEventTypes() != null ? List.copyOf(event.getEventTypes()) : List.of());
        dto.setIsActive(event.getIsActive());
        dto.setEventId(event.getEventId());

        return dto;
    }

    public Event mapFrom(EventDTO eventDTO) {
        if (eventDTO == null) return null;

        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setEventId("EVT-" + new Date().getTime());
        event.setEventTitle(eventDTO.getEventTitle());
        event.setEventOrganizedBy(eventDTO.getEventOrganizedBy());
        event.setEventTime(eventDTO.getEventTime());
        event.setEventLocation(eventDTO.getEventLocation());
        event.setEventTicketPrices(eventDTO.getEventTicketPrices() != null ? List.copyOf(eventDTO.getEventTicketPrices()) : List.of());
        event.setEventTypes(eventDTO.getEventTypes() != null ? List.copyOf(eventDTO.getEventTypes()) : List.of());
        event.setIsActive(eventDTO.getIsActive());

        return event;
    }

}
