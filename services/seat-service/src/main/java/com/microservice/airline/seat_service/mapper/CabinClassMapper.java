package com.microservice.airline.seat_service.mapper;

import com.microservice.airline.payload.request.CabinClassRequest;
import com.microservice.airline.payload.response.CabinClassResponse;
import com.microservice.airline.seat_service.model.CabinClass;

public class CabinClassMapper {

    public static CabinClass toEntity(CabinClassRequest request){
        if(request == null){return null;}

        return CabinClass.builder()
                .name(request.getName())
                .code(request.getCode().toUpperCase())
                .description(request.getDescription())
                .aircraftId(request.getAircraftId())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .isActive(request.getIsActive() == null || request.getIsActive())
                .isBookable(request.getIsBookable() == null || request.getIsBookable())
                .typicalSeatPitch(request.getTypicalSeatPitch())
                .typicalSeatWidth(request.getTypicalSeatWidth())
                .seatType(request.getSeatType())
                .build();
    }

    public static CabinClassResponse toResponse(CabinClass cabinClass){

        if(cabinClass == null){return null;}

        return CabinClassResponse.builder()
                .id(cabinClass.getId())
                .name(cabinClass.getName().name())
                .code(cabinClass.getCode())
                .description(cabinClass.getDescription())
                .aircraftId(cabinClass.getAircraftId())
                //todo: add seatMap to response
               //.seatMap(seatMap != null ? SeatMapMapper.toResponse(seatMap) : null)
                .displayOrder(cabinClass.getDisplayOrder())
                .isActive(cabinClass.getIsActive())
                .isBookable(cabinClass.getIsBookable())
                .typicalSeatPitch(cabinClass.getTypicalSeatPitch())
                .typicalSeatWidth(cabinClass.getTypicalSeatWidth())
                .seatType(cabinClass.getSeatType())
                .createdAt(cabinClass.getCreatedAt())
                .updatedAt(cabinClass.getUpdatedAt())
                .build();

    }

    public static void updateEntity(CabinClass cabinClass, CabinClassRequest request){
        if(cabinClass == null || request == null){return;}

        if(request.getName() !=  null) {cabinClass.setName(request.getName());}
        if(request.getCode() != null) {cabinClass.setCode(request.getCode().toUpperCase());}
        if(request.getDescription() != null) {cabinClass.setDescription(request.getDescription());}
        if(request.getDisplayOrder() != null) {cabinClass.setDisplayOrder(request.getDisplayOrder());}
        if(request.getIsActive() != null) {cabinClass.setIsActive(request.getIsActive());}
        if(request.getIsBookable() != null) {cabinClass.setIsBookable(request.getIsBookable());}
        if(request.getTypicalSeatPitch() != null) {cabinClass.setTypicalSeatPitch(request.getTypicalSeatPitch());}
        if(request.getTypicalSeatWidth() != null) {cabinClass.setTypicalSeatWidth(request.getTypicalSeatWidth());}
        if(request.getSeatType() != null) {cabinClass.setSeatType(request.getSeatType());}
    }



}
