package com.airasia.event_management_service.repository;

import com.airasia.event_management_service.entity.EventSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventSummaryRepository extends JpaRepository<EventSummary, Integer> {
    @Query("SELECT evt FROM EventSummary evt WHERE evt.eventId = ?1")
    Optional<EventSummary> getEventSummaryByEventId(String eventId);
}
