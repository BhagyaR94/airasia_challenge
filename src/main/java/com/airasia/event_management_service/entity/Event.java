package com.airasia.event_management_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "eventId", unique = true)
    private String eventId;
    private String eventTitle;
    private String eventOrganizedBy;
    private Date eventTime;
    private String eventLocation;
    private List<String> eventTicketPrices;
    private List<String> eventTypes;
    private int capacity;
    @Column(name = "isActive")
    private Boolean isActive;
}
