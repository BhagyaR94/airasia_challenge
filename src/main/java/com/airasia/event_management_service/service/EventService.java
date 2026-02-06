package com.airasia.event_management_service.service;

import com.airasia.event_management_service.dto.EventDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);

    Page<EventDTO> loadEvents(final int page, final int size);

}
