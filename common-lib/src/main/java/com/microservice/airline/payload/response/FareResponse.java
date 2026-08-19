package com.microservice.airline.payload.response;

import com.microservice.airline.enums.CabinClassType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FareResponse {

    private Long id;
    private String name;
    private Character rdbCode;
    private Long flightId;
    private Long cabinClassId;
    private CabinClassType cabinClass;

    //pricing details
    private Double baseFare;
    private Double taxesAndFees;
    private Double airlineFees;
    private Double currentPrice;
    private Double totalPrice;
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

    //Relationship
    private Long fareRuleId;
    private FareRulesResponse fareRules;
    private BaggagePolicyResponse baggagePolicy;

    //Audit
    private Instant createdAt;
    private Instant updatedAt;



}
