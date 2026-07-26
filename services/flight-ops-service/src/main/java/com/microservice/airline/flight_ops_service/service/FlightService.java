package com.microservice.airline.flight_ops_service.service;

import com.microservice.airline.enums.FlightStatus;
import com.microservice.airline.payload.request.FlightRequest;
import com.microservice.airline.payload.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {

    FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception;

    Page<FlightResponse> getFlightsByAirline(Long airlineId, Long departureAirportId, Long arrivalAirportId, Pageable pageable);

    FlightResponse getFlightById(Long id) throws Exception;

    FlightResponse updateFlight(Long id, FlightRequest flightRequest) throws Exception;

    FlightResponse changeFlightStatus(Long id, FlightStatus status) throws Exception;

    void deleteFlight(Long airlineId, Long id) throws Exception;
}
