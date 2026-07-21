package com.microservice.airline.payload.request;


import com.microservice.airline.enums.AircraftStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AircraftRequest {

    @NotBlank(message = "Aircraft code is required")
    private String code;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "manufacturer is required")
    private String manufacturer;

    @NotNull(message = "Seating capacity is needed")
    @Positive(message = "Seating capacity  is +ve")
    private Integer seatingCapacity;

    @Positive(message = "Economy seats  is +ve")
    private Integer economySeats;

    @Positive(message = "Premium Economy seats  is +ve")
    private Integer premiumEconomySeats;

    @Positive(message = " Business seats  is +ve")
    private Integer businessSeats;

    @Positive(message = "1st class seats  is +ve")
    private Integer firstClassSeats;

    @Positive(message = "range Km is positive")
    private Integer rangeKm;

    @Positive(message = "Speed should be positive")
    private Integer cruisingSpeedKmh;

    @Positive(message = "Maximum Altitude must be +ve.")
    private Integer maxAltitudeFt;

    @Positive(message = "Year also +ve")
    private Integer yearOfManufacture;

    private LocalDate registrationDate;
    private LocalDate nextMaintenanceDate;

    @NotNull(message = "status is required")
    private AircraftStatus status;

    @NotNull(message = "availability is needed")
    private Boolean isAvailable;

    private Long currentAirportId;








}
