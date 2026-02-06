package com.airasia.event_management_service.validations;

import com.airasia.event_management_service.dto.TicketRequestDTO;

@FunctionalInterface
public interface TicketValidationRule {
    void validate(TicketRequestDTO request);
}
