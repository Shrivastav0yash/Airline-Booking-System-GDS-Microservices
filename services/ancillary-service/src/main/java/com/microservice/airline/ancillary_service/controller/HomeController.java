package com.microservice.airline.ancillary_service.controller;

import com.microservice.airline.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController(){
        return new ApiResponse("Welcome to Ancillary-Service");
    }
}
