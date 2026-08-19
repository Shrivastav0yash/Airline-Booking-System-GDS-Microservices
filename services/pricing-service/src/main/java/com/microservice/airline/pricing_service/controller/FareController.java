package com.microservice.airline.pricing_service.controller;

import com.microservice.airline.payload.request.FareRequest;
import com.microservice.airline.payload.response.ApiResponse;
import com.microservice.airline.payload.response.FareResponse;
import com.microservice.airline.pricing_service.model.Fare;
import com.microservice.airline.pricing_service.service.FareService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fares")
@RequiredArgsConstructor
public class FareController {

    private final FareService fareService;

    @PostMapping
    public ResponseEntity<FareResponse> createFare(
            @Valid @RequestBody FareRequest fareRequest
    ) throws Exception {
        FareResponse createdFare = fareService.createFare(fareRequest);
        return ResponseEntity.ok(createdFare);
    }

    @GetMapping
    public ResponseEntity<?> getFares(){
        return ResponseEntity.ok(fareService.getFares());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFareById(@PathVariable Long id) throws Exception {
        FareResponse fareResponse = fareService.getFareById(id);
        return ResponseEntity.ok(fareResponse);
    }

    @GetMapping("/flight/{flightId}/cabin-class/{cabinClassId}")
    public ResponseEntity<List<FareResponse>> getFaresByFlightIdAndCabinClassId(
            @PathVariable Long flightId,
            @PathVariable Long cabinClassId
    ) {
        return ResponseEntity.ok(fareService.getFaresByFlightIdAndCabinClassId(flightId, cabinClassId));
    }

    @PostMapping("/batch-by-ids")
    public ResponseEntity<Map<Long, FareResponse>> getFarsByIds(@RequestBody List<Long> ids){
        return ResponseEntity.ok(fareService.getFareByIds(ids));
    }

    @PostMapping("/search")
    public ResponseEntity<Map<Long, FareResponse>> getLowestFaresPerFlight(
            @RequestBody List<Long> flightIds,
            @RequestParam Long cabinClassId
    ){
        Map<Long, FareResponse> lowestFares = fareService.getLowestFaresByFlightIds(flightIds, cabinClassId);
        System.out.println("Lowest fares: " + lowestFares.toString());
        return ResponseEntity.ok(lowestFares);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FareResponse> updateFare(
            @PathVariable Long id,
            @Valid @RequestBody FareRequest fareRequest
    ) throws Exception {
        FareResponse updatedFare = fareService.updateFare(id, fareRequest);
        return ResponseEntity.ok(updatedFare);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFare(@PathVariable Long id) throws Exception {
        fareService.deleteFare(id);
        return ResponseEntity.ok(new ApiResponse("Fare deleted successfully"));
    }
}
