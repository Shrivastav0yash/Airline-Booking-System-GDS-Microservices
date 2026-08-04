package com.microservice.airline.flight_ops_service.controller;

import com.microservice.airline.flight_ops_service.service.FlightScheduleService;
import com.microservice.airline.payload.request.FlightScheduleRequest;
import com.microservice.airline.payload.response.ApiResponse;
import com.microservice.airline.payload.response.FlightScheduleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight-schedules")
public class FlightScheduleController {

    private final FlightScheduleService flightScheduleService;

    @PostMapping
    public ResponseEntity<FlightScheduleResponse> createFlightSchedule(
            @Valid @RequestBody  FlightScheduleRequest flightScheduleRequest,
            @RequestHeader("X-Airline-Id") Long airlineId) throws Exception{

        //todo : watch for airline Id
        return ResponseEntity.status(HttpStatus.CREATED).body(flightScheduleService.createFlightSchedule(airlineId, flightScheduleRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightScheduleResponse> getFlightScheduleById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(flightScheduleService.getFlightScheduleById(id));
    }

    @GetMapping
    public ResponseEntity<?> getFlightSchedulesByAirlineId(
            @RequestHeader("X-Airline-Id") Long airlineId
    ){
        return ResponseEntity.ok(flightScheduleService.getFlightScheduleByAirline(airlineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightScheduleResponse> updateFlightSchedule(
            @PathVariable Long id,
            @RequestBody FlightScheduleRequest request
    ) throws Exception {
        return ResponseEntity.ok(flightScheduleService.updateFlightSchedule(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFlightSchedule(@PathVariable Long id) throws Exception {
        flightScheduleService.deleteFlightSchedule(id);
        ApiResponse apiResponse = new ApiResponse("Delete flight schedule successfully");
        return ResponseEntity.ok(apiResponse);
    }

}
