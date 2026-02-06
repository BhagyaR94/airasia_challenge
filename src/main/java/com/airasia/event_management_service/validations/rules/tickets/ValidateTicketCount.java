package com.airasia.event_management_service.validations.rules.tickets;

import com.airasia.event_management_service.dto.TicketRequestDTO;
import com.airasia.event_management_service.exceptions.AppException;
import com.airasia.event_management_service.validations.TicketValidationRule;
import org.springframework.stereotype.Component;

@Component
public class ValidateTicketCount implements TicketValidationRule {
    @Override
    public void validate(TicketRequestDTO request) {
        if (request.getNumberOfTickets() < 0) {
            throw new AppException("Number of tickets must be greater than zero");
        }
    }
}
