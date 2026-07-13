package com.microservice.airline.airline_core_service.controller;

import com.microservice.airline.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse homeController(){
        ApiResponse apiResponse = new ApiResponse(
                "Hey Everyone i am airline core services"
        );
        return apiResponse;
    }
}
