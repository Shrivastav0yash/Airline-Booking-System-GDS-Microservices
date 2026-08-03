package com.microservice.airline.flight_ops_service.service;

import com.microservice.airline.payload.request.FlightScheduleRequest;
import com.microservice.airline.payload.response.FlightScheduleResponse;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface FlightScheduleService {

    FlightScheduleResponse createFlightSchedule(Long airlineId, FlightScheduleRequest flightScheduleRequest) throws Exception;

    FlightScheduleResponse getFlightScheduleById(Long id) throws Exception;

    List<FlightScheduleResponse> getFlightScheduleByAirline(Long airlineId);

    FlightScheduleResponse updateFlightSchedule(Long id, FlightScheduleRequest flightScheduleRequest) throws Exception;

    void deleteFlightSchedule(Long id) throws Exception;

}
