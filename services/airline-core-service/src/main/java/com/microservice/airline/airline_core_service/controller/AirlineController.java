package com.microservice.airline.airline_core_service.controller;

import com.microservice.airline.airline_core_service.service.AirlineService;
import com.microservice.airline.enums.AirlineStatus;
import com.microservice.airline.payload.request.AirlineRequest;
import com.microservice.airline.payload.response.AirlineDropdownItem;
import com.microservice.airline.payload.response.AirlineResponse;
import com.microservice.airline.payload.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(
            @Valid  @RequestBody AirlineRequest airlineRequest,
            @RequestHeader("X-User-Id") long userId
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                airlineService.createAirline(airlineRequest, userId)
        );
    }

    @GetMapping("/admin")
    public ResponseEntity<AirlineResponse> getAirlineByOwner(
            @RequestHeader("X-User-Id") long userId
    ) throws Exception {
        return ResponseEntity.ok(airlineService.getAirlineByOwner(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlineById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(airlineService.getAirlineById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getAllAirlines(Pageable pageable){
        return ResponseEntity.ok(airlineService.getAllAirlines(pageable));
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<AirlineDropdownItem>> getAirlinesForDropdown(){
        return ResponseEntity.ok(airlineService.getAirlineDropdown());
    }

    @PutMapping
    public ResponseEntity<AirlineResponse> updateAirline(
            @Valid @RequestBody AirlineRequest request,
            @RequestHeader("X-User-Id") long userId
    ) throws Exception {
        return ResponseEntity.ok(airlineService.updateAirline(request,userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirline(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") long userId
    ) throws Exception {
        airlineService.deleteAirline(id, userId);
        ApiResponse apiResponse = new ApiResponse("Airline delete sucessfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<AirlineResponse> approveAirline(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.ACTIVE));
    }

    @PutMapping("/{id}/suspend")
    public ResponseEntity<AirlineResponse> suspendAirline(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.INACTIVE));
    }

    @PutMapping("/{id}/ban")
    public ResponseEntity<AirlineResponse> bandAirline(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.BANNED));
    }



}
