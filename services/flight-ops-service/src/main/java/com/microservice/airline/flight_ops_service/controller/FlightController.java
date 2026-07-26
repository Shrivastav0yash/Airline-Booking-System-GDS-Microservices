package com.microservice.airline.flight_ops_service.controller;

import com.microservice.airline.enums.FlightStatus;
import com.microservice.airline.flight_ops_service.service.FlightService;
import com.microservice.airline.payload.request.FlightRequest;
import com.microservice.airline.payload.response.FlightResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(
            @Valid @RequestBody FlightRequest flightRequest,
            @RequestHeader("Airline-Id") Long airlineId // temp for airline Id but when we add API gateway then we use actual User id
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                flightService.createFlight(airlineId,flightRequest)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/airline")
    public ResponseEntity<Page<FlightResponse>>  getFlightsByAirline(
            @RequestHeader("Airline-Id") Long airlineId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            Pageable pageable
    ){
        return ResponseEntity.ok(flightService.getFlightsByAirline(
                airlineId,
                departureAirportId,
                arrivalAirportId,
                pageable
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight(
            @PathVariable Long id,
            @Valid @RequestBody FlightRequest flightRequest
    ) throws Exception {
        return ResponseEntity.ok(flightService.updateFlight(id, flightRequest));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FlightResponse> updateFlightStatus(
            @PathVariable Long id,
            @RequestParam(required = false) FlightStatus status
    ) throws Exception {
        return ResponseEntity.ok(flightService.changeFlightStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(
            @RequestHeader("Airline-Id") Long airlineId,
            @PathVariable Long id) throws Exception {
        flightService.deleteFlight(airlineId,id);
        return ResponseEntity.noContent().build();
    }

}
