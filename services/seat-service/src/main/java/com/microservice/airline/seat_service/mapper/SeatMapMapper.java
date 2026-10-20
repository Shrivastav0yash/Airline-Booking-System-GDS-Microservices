package com.microservice.airline.seat_service.mapper;

import com.microservice.airline.payload.request.SeatMapRequest;
import com.microservice.airline.payload.response.SeatMapResponse;
import com.microservice.airline.seat_service.model.CabinClass;
import com.microservice.airline.seat_service.model.SeatMap;

public class SeatMapMapper {

    public static SeatMap toEntity(SeatMapRequest request, CabinClass cabinClass) {

        return SeatMap.builder()
                .name(request.getName())
                .totalRows(request.getTotalRows())
                .rightSeatsPerRow(request.getRightSeatsPerRow())
                .leftSeatsPerRow(request.getLeftSeatsPerRow())
                .cabinClass(cabinClass)
                .build();
    }

    public static void updateEntity(SeatMap seatMap, SeatMapRequest request) {
        seatMap.setName(request.getName());
        seatMap.setTotalRows(request.getTotalRows());
        seatMap.setRightSeatsPerRow(request.getRightSeatsPerRow());
        seatMap.setLeftSeatsPerRow(request.getLeftSeatsPerRow());
    }

    public static SeatMapResponse toResponse(SeatMap seatMap) {

        if(seatMap == null) {return null;}

        //todo : seat related data is missing now

        return SeatMapResponse.builder()
                .id(seatMap.getId())
                .name(seatMap.getName())
                .totalRows(seatMap.getTotalRows())
                .rightSeatsPerRow(seatMap.getRightSeatsPerRow())
                .leftSeatsPerRow(seatMap.getLeftSeatsPerRow())
                .airlineId(seatMap.getAirlineId())
                .cabinClassId(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getId() : null)
                .cabinClassName(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getName().toString() : null)
                .cabinClassCode(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getCode() : null)
                .build();

    }
}
