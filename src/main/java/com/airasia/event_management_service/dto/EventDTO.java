package com.airasia.event_management_service.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String eventId;
    private String eventTitle;
    private String eventOrganizedBy;
    private Date eventTime;
    private String eventLocation;
    private List<String> eventTicketPrices;
    private List<String> eventTypes;
    private int capacity;
    private Boolean isActive;
}
