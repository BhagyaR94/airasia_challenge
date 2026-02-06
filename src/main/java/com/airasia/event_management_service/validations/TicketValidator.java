package com.airasia.event_management_service.validations;

import com.airasia.event_management_service.dto.TicketRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketValidator {
    private final List<TicketValidationRule> rules;

    public TicketValidator(List<TicketValidationRule> rules) {
        this.rules = rules;
    }

    public void validate(TicketRequestDTO request) {
        for (TicketValidationRule rule : rules) {
            rule.validate(request);
        }
    }
}
