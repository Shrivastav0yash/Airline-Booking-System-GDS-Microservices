package com.microservice.airline.payload.request;

import com.microservice.airline.enums.FlightStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequest {

    @NotBlank(message = "Flight number is needed")
    @Size(max = 10)
    private String flightNumber;

    private Long airlineId;

    @NotNull(message = "Aircraft Id is needed")
    private Long aircraftId;

    @NotNull(message = "departure Airport id is needed")
    private Long departureAirportId;

    @NotNull(message = "arrival airport id is needed")
    private Long arrivalAirportId;

    private FlightStatus status;

}
