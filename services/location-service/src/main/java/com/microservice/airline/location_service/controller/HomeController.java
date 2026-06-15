package com.microservice.airline.location_service.controller;

import com.microservice.airline.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping()
    public ApiResponse HomeController(){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Hello Everyone i am location service from airline system");
        return apiResponse;
    }
}
