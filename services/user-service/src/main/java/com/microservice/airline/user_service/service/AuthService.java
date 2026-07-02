package com.microservice.airline.user_service.service;


import com.microservice.airline.payload.dto.UserDTO;
import com.microservice.airline.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String email, String password);
    AuthResponse signup(UserDTO req) throws Exception;
}
