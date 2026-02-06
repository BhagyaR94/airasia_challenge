package com.airasia.event_management_service.controller;

import com.airasia.event_management_service.dto.EventDTO;
import com.airasia.event_management_service.service.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl eventService;

    @PostMapping("/save")
    public ResponseEntity<EventDTO> createEvent(@RequestBody final EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO));
    }

    @GetMapping("/load")
    public ResponseEntity<Page<EventDTO>> loadEvents(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(eventService.loadEvents(page, size));
    }
}
