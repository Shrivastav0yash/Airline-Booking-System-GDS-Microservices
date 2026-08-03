package com.microservice.airline.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightScheduleRequest {

    @NotNull(message = "Flight Id is needed")
    private Long flightId;

    private Long departureAirportId;
    private Long arrivalAirportId;

    @NotNull(message = "Departure time is needed")
    private LocalTime departureTime;
    @NotNull(message = "Arrival time is needed")
    private LocalTime arrivalTime;

    @NotNull(message = "Start date is needed")
    private LocalDate startDate;
    @NotNull(message = "End date is needed")
    private LocalDate endDate;

    private List<DayOfWeek> operatingDays;

    private Boolean isActive;
}
