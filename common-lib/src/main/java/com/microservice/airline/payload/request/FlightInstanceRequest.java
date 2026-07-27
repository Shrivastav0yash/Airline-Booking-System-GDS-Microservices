package com.microservice.airline.payload.request;

import com.microservice.airline.enums.FlightStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightInstanceRequest {

    @NotNull(message = "Flight ID is required")
    private Long flightId;

    private Long scheduleId;

    private Long departureAirportId;

    private Long arrivalAirportId;

    @NotNull(message = "Departure date-time is mandatory")
    private LocalDateTime departureDateTime;

    @NotNull(message = "Arrival date-time is mandatory")
    private LocalDateTime arrivalDateTime;

    @NotNull(message = "totaL seats is needed")
    @Positive
    private Integer totalSeats;

    @PositiveOrZero
    private Integer availableSeats;

    private FlightStatus status;

    private Integer minAdvancedBookingDays;
    private Integer maxAdvancedBookingDays;
    private Boolean isActive;

}
