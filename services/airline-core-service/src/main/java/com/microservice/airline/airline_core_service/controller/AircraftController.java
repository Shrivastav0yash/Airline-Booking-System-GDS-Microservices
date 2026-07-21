package com.microservice.airline.airline_core_service.controller;

import com.microservice.airline.airline_core_service.service.AircraftService;
import com.microservice.airline.payload.request.AircraftRequest;
import com.microservice.airline.payload.response.AircraftResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aircrafts")
public class AircraftController {

    private final AircraftService aircraftService;

    @PostMapping
    public ResponseEntity<AircraftResponse> createAircraft(
            @Valid  @RequestBody AircraftRequest aircraftRequest,
            @RequestHeader("X-User-Id") Long userId
            ) throws Exception {
        AircraftResponse aircraft = aircraftService.createAircraft(aircraftRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(aircraft);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AircraftResponse> getAircraftById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(aircraftService.getAircraftById(id));
    }

    @GetMapping
    public ResponseEntity<List<AircraftResponse>> listAllAircrafts(
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        return ResponseEntity.ok(aircraftService.listAllAircraftByOwner(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AircraftResponse> updateAircraft(
            @PathVariable Long id,
            @RequestBody AircraftRequest aircraftRequest,
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        return ResponseEntity.ok(aircraftService.updateAircraft(id, aircraftRequest, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Long id, @RequestHeader("X-User-Id") Long userid) throws Exception {
        aircraftService.deleteAircraft(id, userid);
        return ResponseEntity.noContent().build();
    }
}
