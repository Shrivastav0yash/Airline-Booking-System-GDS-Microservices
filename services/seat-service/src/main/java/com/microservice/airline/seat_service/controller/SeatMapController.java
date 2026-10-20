package com.microservice.airline.seat_service.controller;

import com.microservice.airline.payload.request.SeatMapRequest;
import com.microservice.airline.payload.response.ApiResponse;
import com.microservice.airline.payload.response.SeatMapResponse;
import com.microservice.airline.seat_service.service.SeatMapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seat-maps")
public class SeatMapController {

    private final SeatMapService seatMapService;

    @PostMapping
    public ResponseEntity<SeatMapResponse> createSeatMap(
            @Valid @RequestBody SeatMapRequest seatMapRequest,
            @RequestHeader("X-Airline-Id") Long airlineId
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                seatMapService.createSeatMap(
                        airlineId, seatMapRequest
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatMapResponse> getSeatMapById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(seatMapService.getSeatMapById(id));
    }

    @GetMapping("/cabin-class/{cabinClassId}")
    public ResponseEntity<SeatMapResponse> getSeatMapByCabinClass(@PathVariable Long cabinClassId) {
        return ResponseEntity.ok(seatMapService.getSeatMapByCabinClass(cabinClassId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatMapResponse> updateSeatMap(
            @PathVariable Long id,
            @RequestBody SeatMapRequest seatMapRequest
    ) throws Exception {
        return ResponseEntity.ok(seatMapService.updateSeatMap(id, seatMapRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSeatMap(@PathVariable Long id) throws Exception {
        seatMapService.deleteSeatMap(id);
        ApiResponse apiResponse = new ApiResponse("Seat map deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

}
