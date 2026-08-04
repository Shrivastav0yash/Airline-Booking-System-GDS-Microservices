package com.microservice.airline.flight_ops_service.controller;

import com.microservice.airline.flight_ops_service.service.FlightInstanceService;
import com.microservice.airline.payload.request.FlightInstanceRequest;
import com.microservice.airline.payload.response.ApiResponse;
import com.microservice.airline.payload.response.FlightInstanceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/flight-instances")
@RequiredArgsConstructor
public class FlightInstanceController {

    private final FlightInstanceService flightInstanceService;

    @PostMapping
    public ResponseEntity<FlightInstanceResponse> createFlightInstance(
            @Valid @RequestBody FlightInstanceRequest request,
            @RequestHeader("X-Airline-Id") Long airlineId) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                flightInstanceService.createFlightInstance(airlineId, request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightInstanceResponse> getFlightInstanceById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(flightInstanceService.getFlightInstanceById(id));
    }

    @GetMapping
    public ResponseEntity<Page<FlightInstanceResponse>> getFlightInstanceByAirlineId(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            @RequestParam(required = false) Long flightId,
            @RequestParam(required = false)LocalDate onDate,
            Pageable pageable
            ){

        return ResponseEntity.ok(flightInstanceService.getByAirlineId(
                airlineId,
                departureAirportId,
                arrivalAirportId,
                flightId,
                onDate,
                pageable
        )) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightInstanceResponse> updateFlightInstance(
            @PathVariable Long id, @RequestBody FlightInstanceRequest request
    ) throws Exception {
        return  ResponseEntity.ok(flightInstanceService.updateFlightInstance(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFlightInstance(@PathVariable Long id) throws Exception {
        flightInstanceService.deleteFlightInstance(id);
        ApiResponse apiResponse = new ApiResponse("Flight Instance deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
