package com.example.demo.controller;

import com.example.demo.model.entity.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/event")
    public void securedSection(Principal principal) {

        System.out.println(principal);
        System.out.println("Reached secured section");
    }


    @GetMapping("/both")
    public void bothSecuredAndNotSecuredSection(Principal principal) {

        System.out.println(principal);
        System.out.println("Reached both section");
    }


}
