package com.example.demo;

import com.example.demo.model.entity.*;
import com.example.demo.model.entity.converters.EventStatus;
import com.example.demo.model.entity.converters.UserType;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}


@Component
class Runner implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private HallRepository hallRepository;

    @Override
    public void run(String... args) throws Exception {
        createUsers();
        createHalls();
        createEvent();
    }

    private void createHalls() {
        Hall hall_1 = Hall.builder()
                .name("Hall 1")
                .places(200)
                .build();

        Hall hall_2 = Hall.builder()
                .name("Hall 2")
                .places(300)
                .build();

//        hallRepository.save(hall_1);
//        hallRepository.save(hall_2);
        hallRepository.saveAll(Stream.of(hall_1, hall_2).collect(Collectors.toList()));
    }

    private void createEvent() {
        Hall hall = hallRepository.getOne(1);
        LocalDateTime start = LocalDateTime.of(2019, 5, 20, 20, 0);
        Event event = Event.builder()
                .hall(hall)
                .eventName("EuroVision Concert")
                .eventStart(start)
                .eventEnd(start.plusHours(5))
                .eventStatus(EventStatus.SCHEDULED)
                .build();

        eventRepository.save(event);
    }

    private void createUsers() {
        User user_1 = User.builder()
                .userType(UserType.REGULAR)
                .email("john@site.com")
                .firstName("John")
                .lastName("Smith")
                .build();

        userRepository.save(user_1);
    }
}