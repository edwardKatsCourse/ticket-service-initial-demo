package com.example.demo.service.impl;

import com.example.demo.exceptions.EmailDuplicationException;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserSession;
import com.example.demo.model.entity.converters.UserType;
import com.example.demo.model.web.LoginRequest;
import com.example.demo.model.web.LoginResponse;
import com.example.demo.model.web.RegistrationRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserSessionRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new EmailDuplicationException(registrationRequest.getEmail());
        }

        User user = User.builder()
                .userType(UserType.REGULAR)
                .email(registrationRequest.getEmail())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .build();

        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null) {
            UserSession userSession = UserSession.builder()
                    .sessionId(UUID.randomUUID().toString())
                    .user(user)
                    .createdOn(LocalDateTime.now())
                    .isExpired(false)
                    .build();

            userSessionRepository.save(userSession);
            return new LoginResponse(userSession.getSessionId());
        }

        throw new RuntimeException("Credentials are incorrect");
    }
}
