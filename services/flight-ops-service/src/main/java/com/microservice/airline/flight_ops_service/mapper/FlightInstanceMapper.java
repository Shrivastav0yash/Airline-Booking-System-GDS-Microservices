package com.microservice.airline.flight_ops_service.mapper;

import com.microservice.airline.enums.FlightStatus;
import com.microservice.airline.flight_ops_service.model.Flight;
import com.microservice.airline.flight_ops_service.model.FlightInstance;
import com.microservice.airline.payload.request.FlightInstanceRequest;
import com.microservice.airline.payload.response.AircraftResponse;
import com.microservice.airline.payload.response.AirlineResponse;
import com.microservice.airline.payload.response.AirportResponse;
import com.microservice.airline.payload.response.FlightInstanceResponse;

public class FlightInstanceMapper {

    public static FlightInstance toEntity(FlightInstanceRequest flightInstanceRequest, Flight flight) {

        if(flight == null) return null;

        return FlightInstance.builder()
                .flight(flight)
                .airlineId(flight.getAirlineId())
                .scheduleId(flightInstanceRequest.getScheduleId())
                .departureAirportId(flightInstanceRequest.getDepartureAirportId() != null ? flightInstanceRequest.getDepartureAirportId() :  null)
                .arrivalAirportId(flightInstanceRequest.getArrivalAirportId()  != null ? flightInstanceRequest.getArrivalAirportId() :  null)
                .departureDateTime(flightInstanceRequest.getDepartureDateTime())
                .arrivalDateTime(flightInstanceRequest.getArrivalDateTime())
                .status(FlightStatus.SCHEDULED)
                .minAdvancedBookingDays(flightInstanceRequest.getMinAdvancedBookingDays())
                .maxAdvancedBookingDays(flightInstanceRequest.getMaxAdvancedBookingDays())
                .isActive(flightInstanceRequest.getIsActive() != null ? flightInstanceRequest.getIsActive() :  true)
                .build();
    }

    public static FlightInstanceResponse toResponse(FlightInstance fi,
                                                    AircraftResponse aircraftResponse,
                                                    AirlineResponse airline,
                                                    AirportResponse departureAirport,
                                                    AirportResponse arrivalAirport
                                                    ) {
        if(fi == null) return null;

        return FlightInstanceResponse.builder()
                .id(fi.getId())
                .flightId(fi.getFlight() != null ? fi.getFlight().getId() : null)
                .flightNumber(fi.getFlight() != null ? fi.getFlight().getFlightNumber() : null)
                .aircraftId(fi.getFlight().getAircraftId())
                .aircraftModal(aircraftResponse.getModel())
                .aircraftCode(aircraftResponse.getCode())
                .airlineId(fi.getAirlineId())
                .airlineName(airline.getName())
                .airlineLogo(airline.getLogoUrl())
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDateTime(fi.getDepartureDateTime())
                .arrivalDateTime(fi.getArrivalDateTime())
                .formattedDuration(fi.getFormatedDuration())
                .totalSeats(fi.getTotalSeats())
                .availableSeats(fi.getAvailableSeats())
                .status(fi.getStatus())
                .minAdvanceBookingDays(fi.getMinAdvancedBookingDays())
                .maxAdvanceBookingDays(fi.getMaxAdvancedBookingDays())
                .isActive(fi.getIsActive())
                .build();
    }

    public static void updateEntity(FlightInstanceRequest request, FlightInstance existing) {
        if(request == null || existing == null ) return;

        if(request.getDepartureAirportId() != null) existing.setDepartureAirportId(request.getDepartureAirportId());
        if(request.getArrivalAirportId() != null) existing.setArrivalAirportId(request.getArrivalAirportId());
        if(request.getDepartureDateTime() != null) existing.setDepartureDateTime(request.getDepartureDateTime());
        if(request.getArrivalDateTime() != null) existing.setArrivalDateTime(request.getArrivalDateTime());
        if(request.getTotalSeats() != null) existing.setTotalSeats(request.getTotalSeats());
        if(request.getAvailableSeats() != null) existing.setAvailableSeats(request.getAvailableSeats());
        if(request.getStatus() != null) existing.setStatus(request.getStatus());
        if(request.getMinAdvancedBookingDays() != null) existing.setMinAdvancedBookingDays(request.getMinAdvancedBookingDays());
        if(request.getMaxAdvancedBookingDays() != null) existing.setMaxAdvancedBookingDays(request.getMaxAdvancedBookingDays());
        if(request.getIsActive() != null) existing.setIsActive(request.getIsActive());

    }
}
