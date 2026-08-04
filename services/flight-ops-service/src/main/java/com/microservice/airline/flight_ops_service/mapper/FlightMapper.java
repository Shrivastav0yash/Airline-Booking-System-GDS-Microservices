package com.microservice.airline.flight_ops_service.mapper;

import com.microservice.airline.flight_ops_service.model.Flight;
import com.microservice.airline.payload.request.FlightRequest;
import com.microservice.airline.payload.response.AircraftResponse;
import com.microservice.airline.payload.response.AirlineResponse;
import com.microservice.airline.payload.response.AirportResponse;
import com.microservice.airline.payload.response.FlightResponse;

public class FlightMapper {

    public static Flight toEntity(FlightRequest flightRequest) {

        if (flightRequest == null) {return null;}

        return Flight.builder()
                .flightNumber(flightRequest.getFlightNumber())
                .aircraftId(flightRequest.getAircraftId())
                .departureAirportId(flightRequest.getDepartureAirportId())
                .arrivalAirportId(flightRequest.getArrivalAirportId())
                .build();
    }

    public static FlightResponse toResponse(Flight flight,
                                            AircraftResponse aircraft,
                                            AirlineResponse airline,
                                            AirportResponse departureAirport,
                                            AirportResponse arrivalAirport) {

        if (flight == null) {return null;}

        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(airline)
                .aircraft(aircraft)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .status(flight.getStatus())
                .createdAt(flight.getCreatedAt())
                .updatedAt(flight.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FlightRequest request, Flight existing) {

        if (request == null) return;

        if (request.getFlightNumber() != null)
            existing.setFlightNumber(request.getFlightNumber());

        if (request.getAirlineId() != null)
            existing.setAirlineId(request.getAirlineId());

        if (request.getAircraftId() != null)
            existing.setAircraftId(request.getAircraftId());

        if (request.getDepartureAirportId() != null)
            existing.setDepartureAirportId(request.getDepartureAirportId());

        if (request.getArrivalAirportId() != null)
            existing.setArrivalAirportId(request.getArrivalAirportId());

        if (request.getStatus() != null)
            existing.setStatus(request.getStatus());
    }
}
