package com.airasia.event_management_service.service;

import com.airasia.event_management_service.dto.EventDTO;
import com.airasia.event_management_service.entity.Event;
import com.airasia.event_management_service.entity.EventSummary;
import com.airasia.event_management_service.mapper.EventMapper;
import com.airasia.event_management_service.repository.EventRepository;
import com.airasia.event_management_service.repository.EventSummaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final EventSummaryRepository eventSummaryRepository;

    @Override
    @Transactional
    public EventDTO createEvent(EventDTO eventDTO) {
        EventDTO savedEvt = eventMapper.mapTo(eventRepository.save(eventMapper.mapFrom(eventDTO)));
        if (Objects.nonNull(savedEvt)) {
            EventSummary eventSummary = new EventSummary();
            eventSummary.setEventId(savedEvt.getEventId());
            eventSummary.setCapacity(eventDTO.getCapacity());
            eventSummary.setBookings(0);
            eventSummaryRepository.save(eventSummary);
        }
        return savedEvt;
    }

    @Override
    public Page<EventDTO> loadEvents(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = eventRepository.findAllActiveEvents(pageable);
        return eventsPage.map(eventMapper::mapTo);
    }

}
