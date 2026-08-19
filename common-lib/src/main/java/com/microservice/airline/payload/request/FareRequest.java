package com.microservice.airline.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FareRequest {

    @NotBlank(message = "Fare name is required")
    private String name;
    @NotNull(message = "RDB code is required")
    private Character rdbCode;
    @NotNull(message = "Flight ID is required")
    private Long flightId;
    @NotNull(message = "Cabin Class ID is required")
    private Long cabinClassId;

    //pricing details
    @NotNull(message = "Base fare is required")
    @Positive
    private Double baseFare;

    private Double taxesAndFees;
    private Double airlineFees;
    private Double currentPrice;

    @Size(max = 100, message = "Fare label must be less than 100 characters")
    private String fareLabel;

    //Seat benefits
    private Boolean extraSeatSpace;
    private Boolean preferredSeatChoice;
    private Boolean advanceSeatSelection;
    private Boolean guaranteedSeatTogether;

    //Boarding benefits
    private Boolean priorityBoarding;
    private Boolean priorityCheckIn;
    private Boolean fastTrackSecurity;

    //In Flight benefits
    private Boolean complimentaryMeals;
    private Boolean complimentaryBeverages;
    private Boolean premiumMealChoice;
    private Boolean inFlightEntertainment;
    private Boolean inFlightInternet;

    //Flexibility benefits
    private Boolean freeDateChange;
    private Boolean partialRefund;
    private Boolean fullRefund;

    //premium services benefits
    private Boolean loungeAccess;
    private Boolean airportTransfer;
}
