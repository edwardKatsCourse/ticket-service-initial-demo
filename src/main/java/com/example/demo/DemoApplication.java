package com.example.demo;

import com.example.demo.model.entity.Hall;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserRole;
import com.example.demo.model.entity.converters.UserType;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
    private HallRepository hallRepository;

    @Override
    public void run(String... args) throws Exception {
        createUsers();
        createHalls();
    }

    private void createHalls() {
        Hall bigHall = Hall.builder()
                .name("Big Hall")
                .places(500)
                .build();
        Hall smallHall = Hall.builder()
                .name("Small Hall")
                .places(200)
                .build();

        hallRepository.save(bigHall);
        hallRepository.save(smallHall);
    }

    private void createUsers() {
        User user_1 = User.builder()
                .userType(UserType.REGULAR)
                .email("john@site.com")
                .firstName("John")
                .lastName("Smith")
                .userRole(UserRole.ADMIN)
                .build();

        User user_2 = User.builder()
                .userType(UserType.REGULAR)
                .email("peter@site.com")
                .firstName("Peter")
                .lastName("Dale")
                /*.userRole(UserRole.GUEST)*/
                .build();

        User user_3 = User.builder()
                .userType(UserType.REGULAR)
                .email("samantha@site.com")
                .firstName("Samantha")
                .lastName("Powers")
                .build();

        User user_4 = User.builder()
                .userType(UserType.ADMIN)
                .email("samantha-admin@site.com")
                .firstName("Samantha")
                .lastName("Powers")
                .build();

        User user_5 = User.builder()
                .userType(UserType.SUPERUSER)
                .email("samantha-super@site.com")
                .firstName("Samantha")
                .lastName("Powers")
                .build();

        User user_6 = User.builder()
                .userType(UserType.REGULAR)
                .email("peter-p@site.com")
                .firstName("Peter")
                .lastName("Powers")
                .middleName("James")
                .build();

        User user_7 = User.builder()
                .userType(UserType.REGULAR)
                .email("Jane@site.com")
                .firstName("Jane")
                .lastName("Walters")
                .middleName("Mary")
                .build();

        userRepository.saveAll(Arrays.asList(
                user_1,
                user_2,
                user_3,
                user_4,
                user_5,
                user_6,
                user_7));
    }
}