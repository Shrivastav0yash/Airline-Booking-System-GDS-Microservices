package com.microservice.airline.payload.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatMapRequest {

    @NotBlank(message = "Seat map name is required")
    private String name;

    @Positive
    @NotNull(message = "Total rows must be a positive integer")
    private Integer totalRows;

    @NotNull(message = "leftSeatsPerRow must be a positive integer")
    @Positive
    private Integer leftSeatsPerRow;

    @NotNull(message = "rightSeatsPerRow must be a positive integer")
    @Positive
    private Integer rightSeatsPerRow;

    private Long cabinClassId;
}
