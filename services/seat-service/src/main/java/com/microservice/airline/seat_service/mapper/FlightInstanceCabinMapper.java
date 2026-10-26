package com.microservice.airline.seat_service.mapper;


import com.microservice.airline.payload.response.FlightInstanceCabinResponse;
import com.microservice.airline.seat_service.model.FlightInstanceCabin;

public class FlightInstanceCabinMapper {

    public static FlightInstanceCabinResponse toResponse(FlightInstanceCabin fc){

        if(fc == null){ return null;}

        return FlightInstanceCabinResponse.builder()
                .id(fc.getId())
                .flightInstanceId(fc.getFlightInstanceId())
                .cabinClassType(fc.getCabinClass().getName())
                .cabinClass(CabinClassMapper.toResponse(fc.getCabinClass()))
                // todo : set seat instance
                // .seats()
                .seatMap(fc.getCabinClass() != null && fc.getCabinClass().getSeatMap() != null ?
                        SeatMapMapper.toSimpleResponse(fc.getCabinClass().getSeatMap()) : null)
                .totalSeats(fc.getTotalSeats())
                .bookedSeats(fc.getBookedSeats())
                .availableSeats(fc.getAvailableSeats())
                .build();

    }

}
