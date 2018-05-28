package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserSession;
import com.example.demo.model.web.LoginRequest;
import com.example.demo.model.web.LoginResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserSessionRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register() {

    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getEmail() == null ||
                loginRequest.getEmail().isEmpty() ||
                !loginRequest.getEmail().contains("@") ||
                loginRequest.getEmail().length() > 255) {

            throw new RuntimeException("Email format is incorrect");
        }

        return userService.login(loginRequest);
    }

    @PutMapping("/logout")
    public void logout(HttpServletRequest httpRequest) {
        String header = httpRequest.getHeader("Authorization");

        UserSession userSession = userSessionRepository.findBySessionIdAndIsExpiredFalse(header);
        userSession.setIsExpired(true);
    }
}
