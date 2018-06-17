package com.example.demo.controller;

import com.example.demo.exceptions.GeneralAPIException;
import com.example.demo.exceptions.InputValidationException;
import com.example.demo.model.entity.Event;
import com.example.demo.model.entity.Hall;
import com.example.demo.model.entity.converters.EventStatus;
import com.example.demo.model.web.EventRequest;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/secured")
public class EventSecurityController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private HallRepository hallRepository;


    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }


    @Secured(value = "ROLE_ADMIN")
    @PostMapping("/events")
    public Event saveEvent(@RequestBody @Valid EventRequest eventRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }

        Hall hall = hallRepository.findById(eventRequest.getHallId()).orElse(null);
        if (hall == null) {
            throw new GeneralAPIException("Hall with such ID does not exist");
        }

        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochSecond(eventRequest.getEventStart()), ZoneId.systemDefault());
        LocalDateTime end = start.plusHours(eventRequest.getDurationInHours());
        //checks, that there are events in specific hall between start date and end date
        //if false - then there are no events on this time
        //if true - cannot create an event due to the conflict
        boolean hasConflicts = eventRepository.existsEventByEventStartLessThanEqualAndEventEndGreaterThanEqualAndHallEquals(start, end, hall);
        if (hasConflicts) {
            throw new GeneralAPIException("There is an event already scheduled for this time");
        }

        Event event = Event.builder()
                .eventName(eventRequest.getEventTitle())
                .eventStart(start)
                .eventEnd(end)
                .eventStatus(EventStatus.SCHEDULED)
                .hall(hall)
                .build();

        return eventRepository.save(event);
    }

    @PutMapping("/events/{id}")
    public void updateEvent(@PathVariable("id") Integer eventId) {

    }


}
