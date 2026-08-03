package com.microservice.airline.flight_ops_service.service.Impl;

import com.microservice.airline.enums.FlightStatus;
import com.microservice.airline.flight_ops_service.mapper.FlightScheduleMapper;
import com.microservice.airline.flight_ops_service.model.Flight;
import com.microservice.airline.flight_ops_service.model.FlightSchedule;
import com.microservice.airline.flight_ops_service.repository.FlightRepository;
import com.microservice.airline.flight_ops_service.repository.FlightScheduleRepository;
import com.microservice.airline.flight_ops_service.service.FlightInstanceService;
import com.microservice.airline.flight_ops_service.service.FlightScheduleService;
import com.microservice.airline.payload.request.FlightInstanceRequest;
import com.microservice.airline.payload.request.FlightScheduleRequest;
import com.microservice.airline.payload.response.AirportResponse;
import com.microservice.airline.payload.response.FlightScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private final FlightScheduleRepository flightScheduleRepository;
    private final FlightRepository flightRepository;
    private final FlightInstanceService flightInstanceService;


    @Override
    public FlightScheduleResponse createFlightSchedule(Long airlineId, FlightScheduleRequest flightScheduleRequest) throws Exception {

        //todo : change airlineid to userId
        Flight flight = flightRepository.findById(flightScheduleRequest.getFlightId())
                .orElseThrow(() -> new Exception("Flight not found"));

        if(flightScheduleRequest.getEndDate().isBefore(flightScheduleRequest.getStartDate())) {
            throw new Exception("End date cannot be before start date");
        }

        FlightSchedule schedule = FlightScheduleMapper.toEntity(flightScheduleRequest, flight);
        FlightSchedule saved = flightScheduleRepository.save(schedule);

        //create flight instance

        List<DayOfWeek> operatingDays = saved.getOperatingDays();
        LocalDate startDate = saved.getStartDate();
        LocalDate endDate = saved.getEndDate();

        FlightInstanceRequest  flightInstanceRequest = FlightInstanceRequest.builder()
                .scheduleId(saved.getId())
                .flightId(flight.getId())
                .arrivalAirportId(flight.getArrivalAirportId())
                .departureAirportId(flight.getDepartureAirportId())
                .status(FlightStatus.SCHEDULED)
                .build();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if(operatingDays.contains(date.getDayOfWeek())) {
                flightInstanceRequest.setDepartureDateTime(
                        LocalDateTime.of(date, saved.getDepartureTime())
                );
                flightInstanceRequest.setArrivalDateTime(
                        LocalDateTime.of(date, saved.getArrivalTime())
                );
                flightInstanceService.createFlightInstance(airlineId, flightInstanceRequest);
            }
        }

        return convertToFlightScheduleResponse(saved);
    }

    @Override
    public FlightScheduleResponse getFlightScheduleById(Long id) throws Exception {
        FlightSchedule flightSchedule = flightScheduleRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight Schedule not found")
        );
        return convertToFlightScheduleResponse(flightSchedule);
    }

    @Override
    public List<FlightScheduleResponse> getFlightScheduleByAirline(Long airlineId) {
        //todo : watch airlineId
        List<FlightSchedule> schedules = flightScheduleRepository.findByFlightAirlineId(airlineId);
        return schedules.stream()
                .map(this::convertToFlightScheduleResponse)
                .toList();
    }

    @Override
    public FlightScheduleResponse updateFlightSchedule(Long id, FlightScheduleRequest flightScheduleRequest) throws Exception {

        FlightSchedule flightSchedule = flightScheduleRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight Schedule not found")
        );
        FlightScheduleMapper.updateEntity(flightScheduleRequest, flightSchedule);
        FlightSchedule updatedScheduled = flightScheduleRepository.save(flightSchedule);
        return convertToFlightScheduleResponse(updatedScheduled);
    }

    @Override
    public void deleteFlightSchedule(Long id) throws Exception {

        FlightSchedule flightSchedule = flightScheduleRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight Schedule not found")
        );
        flightScheduleRepository.delete(flightSchedule);

    }

    private FlightScheduleResponse convertToFlightScheduleResponse(FlightSchedule flightSchedule) {

        //todo : service to service communication
        AirportResponse departureAirport = AirportResponse.builder()
                .id(flightSchedule.getDepartureAirportId())
                .build();
        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flightSchedule.getArrivalAirportId())
                .build();

        return FlightScheduleMapper.toResponse(flightSchedule, departureAirport, arrivalAirport);

    }
}
