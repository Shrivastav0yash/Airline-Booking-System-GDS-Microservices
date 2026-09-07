package com.microservice.airline.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaggagePolicyRequest {

    @NotBlank(message = "Baggage policy name is required")
    private String name;

    @NotNull(message = "Fare ID is required")
    private Long fareId;

    private String description;

    @PositiveOrZero
    private Double cabinBaggageMaxWeight;

    @PositiveOrZero
    private Integer cabinBaggagePieces;

    @PositiveOrZero
    private Integer cabinBaggageWeightPerPiece;


    private Integer cabinBaggageMaxDimensions;

    @PositiveOrZero
    private Double checkInBaggageMaxWeight;

    @PositiveOrZero
    private Integer checkInBaggagePieces;

    @PositiveOrZero
    private Double checkInBaggageWeightPerPiece;

    @PositiveOrZero
    private Integer freeCheckedBagsAllowance;

    //Benefits
    private Boolean priorityBaggage;
    private Boolean extraBaggageAllowance;


}
