package com.airasia.event_management_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TicketDTO {
    private Long id;
    private String ticketNumber;
    private String eventID;
    private String issuedFor;
    private String issuedBy;
    private Date issuedAt;
    private Boolean isValid;
}
