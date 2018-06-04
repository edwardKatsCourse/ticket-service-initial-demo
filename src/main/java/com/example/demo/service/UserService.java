package com.example.demo.service;

import com.example.demo.model.web.LoginRequest;
import com.example.demo.model.web.LoginResponse;
import com.example.demo.model.web.RegistrationRequest;

public interface UserService {


    void register(RegistrationRequest registrationRequest);

    LoginResponse login(LoginRequest loginRequest);
}
