package com.microservice.airline.pricing_service.mapper;

import com.microservice.airline.payload.request.BaggagePolicyRequest;
import com.microservice.airline.payload.response.BaggagePolicyResponse;
import com.microservice.airline.pricing_service.model.BaggagePolicy;
import com.microservice.airline.pricing_service.model.Fare;

public class BaggagePolicyMapper {

    public static BaggagePolicy toEntity(BaggagePolicyRequest request, Fare fare) {

        if(request == null) {return null;}

        return BaggagePolicy.builder()
                .fare(fare)
                .name(request.getName())
                .description(request.getDescription())
                .cabinBaggageMaxWeight(request.getCabinBaggageMaxWeight())
                .cabinBaggagePieces(request.getCabinBaggagePieces() != null ? request.getCabinBaggagePieces() : 1)
                .cabinBaggageWeightPerPiece(request.getCabinBaggageWeightPerPiece())
                .cabinBaggageMaxDimensions(request.getCabinBaggageMaxDimensions())

                .checkInBaggageMaxWeight(request.getCheckInBaggageMaxWeight())
                .checkInBaggagePieces(request.getCheckInBaggagePieces() != null ? request.getCheckInBaggagePieces() : 1)
                .checkInBaggageWeightPerPiece(request.getCheckInBaggageWeightPerPiece())
                .freeCheckedBagsAllowance(request.getFreeCheckedBagsAllowance() != null ? request.getFreeCheckedBagsAllowance() : 0)
                .priorityBaggage(request.getPriorityBaggage() != null ? request.getPriorityBaggage() : false)
                .extraBaggageAllowance(request.getExtraBaggageAllowance() != null ? request.getExtraBaggageAllowance() : false)
                .build();

    }

    public static BaggagePolicyResponse toResponse(BaggagePolicy baggagePolicy) {
        if(baggagePolicy == null) {return null;}

        return BaggagePolicyResponse.builder()
                .id(baggagePolicy.getId())
                .name(baggagePolicy.getName())
                .description(baggagePolicy.getDescription())
                .cabinBaggageMaxWeight(baggagePolicy.getCabinBaggageMaxWeight())
                .cabinBaggagePieces(baggagePolicy.getCabinBaggagePieces())
                .cabinBaggageWeightPerPiece(baggagePolicy.getCabinBaggageWeightPerPiece())
                .cabinBaggageMaxDimensions(baggagePolicy.getCabinBaggageMaxDimensions())

                .checkInBaggageMaxWeight(baggagePolicy.getCheckInBaggageMaxWeight())
                .checkInBaggagePieces(baggagePolicy.getCheckInBaggagePieces())
                .checkInBaggageWeightPerPiece(baggagePolicy.getCheckInBaggageWeightPerPiece())

                .freeCheckedBagsAllowance(baggagePolicy.getFreeCheckedBagsAllowance())
                .priorityBaggage(baggagePolicy.getPriorityBaggage())
                .extraBaggageAllowance(baggagePolicy.getExtraBaggageAllowance())

                .airlineId(baggagePolicy.getAirlineId())
                .fareId(baggagePolicy.getFare() != null ? baggagePolicy.getFare().getId() : null)

                .createdAt(baggagePolicy.getCreatedAt())
                .updateAt(baggagePolicy.getUpdatedAt())
                .build();
    }

    public static void updateEntity(BaggagePolicy baggagePolicy, BaggagePolicyRequest request) {
        if(baggagePolicy == null || request == null) {return;}

        if(request.getName() != null) baggagePolicy.setName(request.getName());
        if(request.getDescription() != null) baggagePolicy.setDescription(request.getDescription());
        if(request.getCabinBaggageMaxWeight() != null) baggagePolicy.setCabinBaggageMaxWeight(request.getCabinBaggageMaxWeight());
        if(request.getCabinBaggagePieces() != null) baggagePolicy.setCabinBaggagePieces(request.getCabinBaggagePieces());
        if(request.getCabinBaggageWeightPerPiece() != null) baggagePolicy.setCabinBaggageWeightPerPiece(request.getCabinBaggageWeightPerPiece());
        if(request.getCabinBaggageMaxDimensions() != null) baggagePolicy.setCabinBaggageMaxDimensions(request.getCabinBaggageMaxDimensions());
        if(request.getCheckInBaggageMaxWeight() != null) baggagePolicy.setCheckInBaggageMaxWeight(request.getCheckInBaggageMaxWeight());
        if(request.getCheckInBaggagePieces() != null) baggagePolicy.setCheckInBaggagePieces(request.getCheckInBaggagePieces());
        if(request.getCheckInBaggageWeightPerPiece() != null) baggagePolicy.setCheckInBaggageWeightPerPiece(request.getCheckInBaggageWeightPerPiece());
        if(request.getFreeCheckedBagsAllowance() != null) baggagePolicy.setFreeCheckedBagsAllowance(request.getFreeCheckedBagsAllowance());
        if(request.getPriorityBaggage() != null) baggagePolicy.setPriorityBaggage(request.getPriorityBaggage());
        if(request.getExtraBaggageAllowance() != null) baggagePolicy.setExtraBaggageAllowance(request.getExtraBaggageAllowance());

    }
}
