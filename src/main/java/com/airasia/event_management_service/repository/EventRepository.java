package com.airasia.event_management_service.repository;

import com.airasia.event_management_service.entity.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT evt FROM Event evt WHERE evt.isActive = true")
    Page<Event> findAllActiveEvents(Pageable pageable);
}
