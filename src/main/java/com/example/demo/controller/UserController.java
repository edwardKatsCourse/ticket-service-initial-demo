package com.example.demo.controller;

import com.example.demo.exceptions.InputValidationException;
import com.example.demo.model.entity.UserSession;
import com.example.demo.model.web.LoginRequest;
import com.example.demo.model.web.LoginResponse;
import com.example.demo.model.web.RegistrationRequest;
import com.example.demo.repository.UserSessionRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegistrationRequest registrationRequest,
                         BindingResult result) {

        if (result.hasErrors()) {

            throw new InputValidationException(result);
        }
        userService.register(registrationRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest,
                               BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
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
