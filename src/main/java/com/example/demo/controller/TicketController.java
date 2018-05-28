package com.example.demo.controller;

import com.example.demo.model.entity.Ticket;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/tickets/buy")
public class TicketController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public void buyTicket(Principal principal) {
        if (principal != null) {
            userRepository.findByEmail(principal.getName());
        }
    }

}
