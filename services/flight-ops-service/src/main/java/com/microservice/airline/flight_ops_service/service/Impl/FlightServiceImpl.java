package com.microservice.airline.flight_ops_service.service.Impl;

import com.microservice.airline.enums.FlightStatus;
import com.microservice.airline.flight_ops_service.mapper.FlightMapper;
import com.microservice.airline.flight_ops_service.model.Flight;
import com.microservice.airline.flight_ops_service.repository.FlightRepository;
import com.microservice.airline.flight_ops_service.service.FlightService;
import com.microservice.airline.payload.request.FlightRequest;
import com.microservice.airline.payload.response.AircraftResponse;
import com.microservice.airline.payload.response.AirlineResponse;
import com.microservice.airline.payload.response.AirportResponse;
import com.microservice.airline.payload.response.FlightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;


    @Override
    public FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception {
        if(flightRepository.existsByFlightNumber(flightRequest.getFlightNumber())) {
            throw new Exception("flight already exist with this flight number");
        }
        Flight flight = FlightMapper.toEntity(flightRequest);
        flight.setAirlineId(airlineId);
        Flight savedFlight = flightRepository.save(flight);
        return convertToFlightResponse(savedFlight);
    }

    @Override
    public Page<FlightResponse> getFlightsByAirline(Long airlineId, Long departureAirportId, Long arrivalAirportId, Pageable pageable) {

        return flightRepository.findByAirlineId(airlineId,departureAirportId,arrivalAirportId, pageable)
                .map(this::convertToFlightResponse);
    }

    @Override
    public FlightResponse getFlightById(Long id) throws Exception {
        Flight flight = flightRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight not exist with this Id :" +id)
        );
        return convertToFlightResponse(flight);
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) throws Exception {
        Flight existingFlight = flightRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight not exist with this id")
        );
        if(flightRequest.getFlightNumber() != null &&
                flightRepository.existsByFlightNumberAndIdNot(flightRequest.getFlightNumber(), id)) {
            throw new Exception("flight already exist with this flight number");
        }
        FlightMapper.updateEntity(flightRequest, existingFlight);
        Flight updatedFlight = FlightMapper.toEntity(flightRequest);
        flightRepository.save(updatedFlight);
        return convertToFlightResponse(updatedFlight);
    }

    @Override
    public FlightResponse changeFlightStatus(Long id, FlightStatus status) throws Exception {
        Flight existingFlight = flightRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight not exist with this id")
        );
        existingFlight.setStatus(status);
        Flight savedFlight = flightRepository.save(existingFlight);
        return convertToFlightResponse(savedFlight);
    }

    @Override
    public void deleteFlight(Long airlineId, Long id) throws Exception {
        // for delete that's why it is empty
        Flight existingFlight = flightRepository.findByAirlineIdAndIdNot(airlineId,id).orElseThrow(
                ()-> new Exception("Flight not exist with this id")
        );
        flightRepository.delete(existingFlight);

    }

    public FlightResponse convertToFlightResponse(Flight flight) {
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(flight.getAircraftId())
                .build();
        AirlineResponse airline = AirlineResponse.builder()
                .id(flight.getAirlineId())
                .build();
        AirportResponse departureAirport = AirportResponse.builder()
                .id(flight.getDepartureAirportId())
                .build();
        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flight.getArrivalAirportId())
                .build();
        return FlightMapper.toResponse(flight,aircraft, airline, departureAirport, arrivalAirport);
    }
}
