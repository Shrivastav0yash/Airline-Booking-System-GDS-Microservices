package com.microservice.airline.pricing_service.mapper;

import com.microservice.airline.embeddable.*;
import com.microservice.airline.payload.request.FareRequest;
import com.microservice.airline.payload.response.FareResponse;
import com.microservice.airline.pricing_service.model.Fare;

public class FareMapper {

    public static Fare toEntity(FareRequest request){

        if(request == null){return null;}

        Double calculatedPrice = request.getCurrentPrice();
        if(calculatedPrice == null){
            calculatedPrice = request.getTaxesAndFees() + request.getAirlineFees() + request.getBaseFare();
        }

        SeatBenefits seatBenefits = SeatBenefits.builder()
                .extraSeatSpace(bool(request.getExtraSeatSpace()))
                .preferredSeatChoice(bool(request.getPreferredSeatChoice()))
                .advanceSeatSelection(bool(request.getAdvanceSeatSelection()))
                .guaranteedSeatTogether(bool(request.getGuaranteedSeatTogether()))
                .build();

        BoardingBenefits boardingBenefits = BoardingBenefits.builder()
                .priorityBoarding(bool(request.getPriorityBoarding()))
                .priorityCheckIn(bool(request.getPriorityCheckIn()))
                .fastTrackSecurity(bool(request.getFastTrackSecurity()))
                .build();

        InFlightBenefits inFlightBenefits = InFlightBenefits.builder()
                .complimentaryBeverages(bool(request.getComplimentaryBeverages()))
                .complimentaryMeals(bool(request.getComplimentaryMeals()))
                .inFlightEntertainment(bool(request.getInFlightEntertainment()))
                .inFlightInternet(bool(request.getInFlightInternet()))
                .premiumMealChoice(bool(request.getPremiumMealChoice()))
                .build();

        FlexibilityBenefits flexibilityBenefits = FlexibilityBenefits.builder()
                .freeDateChange(bool(request.getFreeDateChange()))
                .partialRefund(bool(request.getPartialRefund()))
                .fullRefund(bool(request.getFullRefund()))
                .build();

        PremiumServiceBenefits premiumServiceBenefits = PremiumServiceBenefits.builder()
                .loungeAccess(bool(request.getLoungeAccess()))
                .airportTransfer(bool(request.getAirportTransfer()))
                .build();

        return Fare.builder()
                .name(request.getName())
                .rdbCode(request.getRdbCode())
                .flightId(request.getFlightId())
                .cabinClassId(request.getCabinClassId())
                .baseFare(request.getBaseFare())
                .taxesAndFees(request.getTaxesAndFees())
                .airlineFees(request.getAirlineFees())
                .currentPrice(calculatedPrice)
                .fareLabel(request.getFareLabel())
                .seatBenefits(seatBenefits)
                .boardingBenefits(boardingBenefits)
                .inFlightBenefits(inFlightBenefits)
                .flexibilityBenefits(flexibilityBenefits)
                .premiumServiceBenefits(premiumServiceBenefits)
                .build();
    }

    public static FareResponse toResponse(Fare fare){

        if(fare == null){return null;}

        return FareResponse.builder()
                .id(fare.getId())
                .name(fare.getName())
                .rdbCode(fare.getRdbCode())
                .flightId(fare.getFlightId())
                .cabinClassId(fare.getCabinClassId())
                .cabinClass(fare.getCabinClass())
                .baseFare(fare.getBaseFare())
                .taxesAndFees(fare.getTaxesAndFees())
                .airlineFees(fare.getAirlineFees())
                .currentPrice(fare.getCurrentPrice())
                .totalPrice(fare.getTotalPrice())
                .fareLabel(fare.getFareLabel())
                .fareRuleId(fare.getFareRules() != null ? fare.getFareRules().getId() : null)

                //Seat Benefits
                .extraSeatSpace(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getExtraSeatSpace() : false)
                .preferredSeatChoice(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getPreferredSeatChoice() : false)
                .advanceSeatSelection(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getAdvanceSeatSelection() : false)
                .guaranteedSeatTogether(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getGuaranteedSeatTogether() : false)

                //Boarding Benefits
                .priorityBoarding(fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getPriorityBoarding() : false)
                .priorityCheckIn(fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getPriorityCheckIn() : false)
                .fastTrackSecurity(fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getFastTrackSecurity() : false)

                //In-Flight Benefits
                .complimentaryBeverages(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getComplimentaryBeverages() : false)
                .complimentaryMeals(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getComplimentaryMeals() : false)
                .inFlightEntertainment(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getInFlightEntertainment() : false)
                .inFlightInternet(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getInFlightInternet() : false)
                .premiumMealChoice(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getPremiumMealChoice() : false)

                //Flexibility Benefits
                .freeDateChange(fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getFreeDateChange() : false)
                .partialRefund(fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getPartialRefund() : false)
                .fullRefund(fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getFullRefund() : false)

                //Premium Service Benefits
                .loungeAccess(fare.getPremiumServiceBenefits() != null ? fare.getPremiumServiceBenefits().getLoungeAccess() : false)
                .airportTransfer(fare.getPremiumServiceBenefits() != null ? fare.getPremiumServiceBenefits().getAirportTransfer() : false)

                .fareRules(fare.getFareRules() != null ? FareRulesMapper.toResponse(fare.getFareRules()) : null)
                .baggagePolicy(fare.getBaggagePolicy() != null ? BaggagePolicyMapper.toResponse(fare.getBaggagePolicy()) : null)

                .createdAt(fare.getCreatedAt())
                .updatedAt(fare.getUpdatedAt())
                .build();

    }

    public static void updateEntity(FareRequest request, Fare existing){

        if(request == null || existing == null) return;

        if(request.getName() != null) existing.setName(request.getName());
        if(request.getRdbCode() != null) existing.setRdbCode(request.getRdbCode());
        if(request.getFlightId() != null) existing.setFlightId(request.getFlightId());
        if(request.getCabinClassId() != null) existing.setCabinClassId(request.getCabinClassId());

        if(request.getBaseFare() != null) existing.setBaseFare(request.getBaseFare());
        if(request.getTaxesAndFees() != null) existing.setTaxesAndFees(request.getTaxesAndFees());
        if(request.getAirlineFees() != null) existing.setAirlineFees(request.getAirlineFees());
        if(request.getCurrentPrice() != null) existing.setCurrentPrice(request.getCurrentPrice());
        if(request.getFareLabel() != null) existing.setFareLabel(request.getFareLabel());

        //embedded benefits update
        SeatBenefits sb = existing.getSeatBenefits();
        if(request.getExtraSeatSpace() != null) sb.setExtraSeatSpace(request.getExtraSeatSpace());
        if(request.getPreferredSeatChoice() != null) sb.setPreferredSeatChoice(request.getPreferredSeatChoice());
        if(request.getAdvanceSeatSelection() != null) sb.setAdvanceSeatSelection(request.getAdvanceSeatSelection());
        if(request.getGuaranteedSeatTogether() != null) sb.setGuaranteedSeatTogether(request.getGuaranteedSeatTogether());

        BoardingBenefits bb = existing.getBoardingBenefits();
        if(request.getPriorityBoarding() != null) bb.setPriorityBoarding(request.getPriorityBoarding());
        if(request.getPriorityCheckIn() != null) bb.setPriorityCheckIn(request.getPriorityCheckIn());
        if(request.getFastTrackSecurity() != null) bb.setFastTrackSecurity(request.getFastTrackSecurity());

        InFlightBenefits ifb = existing.getInFlightBenefits();
        if(request.getComplimentaryBeverages() != null) ifb.setComplimentaryBeverages(request.getComplimentaryBeverages());
        if(request.getComplimentaryMeals() != null) ifb.setComplimentaryMeals(request.getComplimentaryMeals());
        if(request.getInFlightEntertainment() != null) ifb.setInFlightEntertainment(request.getInFlightEntertainment());
        if(request.getInFlightInternet() != null) ifb.setInFlightInternet(request.getInFlightInternet());
        if(request.getPremiumMealChoice() != null) ifb.setPremiumMealChoice(request.getPremiumMealChoice());

        FlexibilityBenefits fb = existing.getFlexibilityBenefits();
        if(request.getFreeDateChange() != null) fb.setFreeDateChange(request.getFreeDateChange());
        if(request.getPartialRefund() != null) fb.setPartialRefund(request.getPartialRefund());
        if(request.getFullRefund() != null) fb.setFullRefund(request.getFullRefund());

        PremiumServiceBenefits psb = existing.getPremiumServiceBenefits();
        if(request.getLoungeAccess() != null) psb.setLoungeAccess(request.getLoungeAccess());
        if(request.getAirportTransfer() != null) psb.setAirportTransfer(request.getAirportTransfer());

    }

    private static Boolean bool(Boolean value){
        return value != null ? value : false;
    }


}
