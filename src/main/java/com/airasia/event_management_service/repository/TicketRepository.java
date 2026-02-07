package com.airasia.event_management_service.repository;

import com.airasia.event_management_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.ticketNumber IN :ticketIds")
    Optional<List<Ticket>> findTickets(@Param("ticketIds") List<String> ticketIds);

}
