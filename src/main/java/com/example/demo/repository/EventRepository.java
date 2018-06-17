package com.example.demo.repository;

import com.example.demo.model.entity.Event;
import com.example.demo.model.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsEventByEventStartLessThanEqualAndEventEndGreaterThanEqualAndHallEquals(LocalDateTime start, LocalDateTime end, Hall hall);
}
