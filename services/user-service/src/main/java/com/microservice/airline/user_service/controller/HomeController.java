package com.microservice.airline.user_service.controller;

import com.microservice.airline.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController(){
        ApiResponse apiResponse = new ApiResponse("Welcome to User-Service");
        return apiResponse;

    }
}
