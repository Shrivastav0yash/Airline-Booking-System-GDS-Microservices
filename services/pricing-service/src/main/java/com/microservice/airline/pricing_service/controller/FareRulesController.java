package com.microservice.airline.pricing_service.controller;

import com.microservice.airline.payload.request.FareRulesRequest;
import com.microservice.airline.payload.response.ApiResponse;
import com.microservice.airline.payload.response.FareRulesResponse;
import com.microservice.airline.pricing_service.service.FareRulesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fare-rules")
@RequiredArgsConstructor
public class FareRulesController {

    private final FareRulesService fareRulesService;

    @PostMapping
    public ResponseEntity<FareRulesResponse> createFareRules(
            @Valid @RequestBody FareRulesRequest fareRulesRequest

    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                fareRulesService.createFareRules(fareRulesRequest)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FareRulesResponse> getFareRulesById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(fareRulesService.getFareRulesById(id));
    }

    @GetMapping("/fare/{fareId}")
    public ResponseEntity<FareRulesResponse> getFareRulesByFareId(@PathVariable Long fareId) throws Exception {
        return ResponseEntity.ok(fareRulesService.getFareRulesByFareId(fareId));
    }

    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<FareRulesResponse>> getFareByAirlineId(@PathVariable Long airlineId) throws Exception {
        return ResponseEntity.ok(fareRulesService.getFareRulesByAirlineId(airlineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FareRulesResponse> updateFareRules(
            @PathVariable Long id,
            @Valid @RequestBody FareRulesRequest fareRulesRequest
    ) throws Exception {
        return ResponseEntity.ok(fareRulesService.updateFareRules(id, fareRulesRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFareRules(@PathVariable Long id) throws Exception {
        fareRulesService.deleteFareRules(id);
        return ResponseEntity.ok(new ApiResponse("Deletion successfully"));
    }

}
