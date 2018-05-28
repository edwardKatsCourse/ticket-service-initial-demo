package com.example.demo.service;

import com.example.demo.model.web.LoginRequest;
import com.example.demo.model.web.LoginResponse;

public interface UserService {



    LoginResponse login(LoginRequest loginRequest);
}
