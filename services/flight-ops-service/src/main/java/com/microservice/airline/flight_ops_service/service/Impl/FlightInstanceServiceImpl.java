package com.microservice.airline.flight_ops_service.service.Impl;

import com.microservice.airline.flight_ops_service.mapper.FlightInstanceMapper;
import com.microservice.airline.flight_ops_service.model.Flight;
import com.microservice.airline.flight_ops_service.model.FlightInstance;
import com.microservice.airline.flight_ops_service.repository.FlightInstanceRepository;
import com.microservice.airline.flight_ops_service.repository.FlightRepository;
import com.microservice.airline.flight_ops_service.service.FlightInstanceService;
import com.microservice.airline.payload.request.FlightInstanceRequest;
import com.microservice.airline.payload.response.AircraftResponse;
import com.microservice.airline.payload.response.AirlineResponse;
import com.microservice.airline.payload.response.AirportResponse;
import com.microservice.airline.payload.response.FlightInstanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightInstanceServiceImpl implements FlightInstanceService {

    private final FlightRepository flightRepository;
    private final FlightInstanceRepository flightInstanceRepository;

    @Override
    public FlightInstanceResponse createFlightInstance(Long airlineId, FlightInstanceRequest request) throws Exception {

        // todo : watch airlineId shoukd not use intead we use userId
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(
                ()-> new Exception("Flight not Found")
        );

        // todo : service to service communication - pending
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(1L)
                .totalSeats(90)
                .build();

        FlightInstance flightInstance = FlightInstanceMapper.toEntity(request, flight);
        flightInstance.setTotalSeats(aircraft.getTotalSeats());
        flightInstance.setAvailableSeats(aircraft.getTotalSeats());

        FlightInstance saved = flightInstanceRepository.save(flightInstance);

        // todo : create seat instances - do it later

        return convertToFlightInstanceResponse(saved);
    }

    @Override
    public FlightInstanceResponse getFlightInstanceById(Long id) throws Exception {

        FlightInstance flightInstance = flightInstanceRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight Instance not found with id "+id)
        );
        return convertToFlightInstanceResponse(flightInstance);
    }

    @Override
    public Page<FlightInstanceResponse> getByAirlineId(Long airlineId,
                                                       Long departureAirportId,
                                                       Long arrivalAirportId,
                                                       Long flightId,
                                                       LocalDate onDate,
                                                       Pageable pageable) {
        // todo : watch airlineId shoukd not use intead we use userId

        LocalDateTime start = onDate != null ? onDate.atStartOfDay() : null;
        LocalDateTime end = onDate != null ? onDate.plusDays(1).atStartOfDay() : null;
        return flightInstanceRepository.findByAirlineId(
                airlineId, departureAirportId, arrivalAirportId, flightId, start, end, pageable
                ).map(this::convertToFlightInstanceResponse);
    }

    @Override
    public FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest request) throws Exception {

        FlightInstance exisiting = flightInstanceRepository.findById(id).orElseThrow(
                () -> new Exception("Flight instance not found")
        );
        FlightInstanceMapper.updateEntity(request, exisiting);
        return convertToFlightInstanceResponse(flightInstanceRepository.save(exisiting));
    }

    @Override
    public void deleteFlightInstance(Long id) throws Exception {

        FlightInstance exisiting = flightInstanceRepository.findById(id).orElseThrow(
                () -> new Exception("Flight instance not found")
        );
        flightInstanceRepository.delete(exisiting);

    }

    private FlightInstanceResponse convertToFlightInstanceResponse(FlightInstance flightInstance) {
        //todo service to service communication pending
        AirlineResponse airline = AirlineResponse.builder()
                .id(flightInstance.getAirlineId())
                .build();
        AirportResponse departureAirport = AirportResponse.builder()
                .id(flightInstance.getDepartureAirportId())
                .build();
        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flightInstance.getArrivalAirportId())
                .build();
        AircraftResponse aircraftResponse = AircraftResponse.builder()
                .id(flightInstance.getFlight().getAircraftId())
                .build();
        return FlightInstanceMapper.toResponse(flightInstance,aircraftResponse, airline,departureAirport,arrivalAirport);
    }
}
