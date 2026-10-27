package com.microservice.airline.seat_service.mapper;

import com.microservice.airline.enums.SeatAvailabilityStatus;
import com.microservice.airline.payload.response.SeatInstanceResponse;
import com.microservice.airline.seat_service.model.SeatInstance;

public class SeatInstanceMapper {

    public static SeatInstanceResponse toResponse(SeatInstance seatInstance){
        return SeatInstanceResponse.builder()
                .id(seatInstance.getId())
                .flightId(seatInstance.getFlightId())
                .seatId(seatInstance.getSeat() != null ? seatInstance.getSeat().getId() : null)
                .seatNumber(seatInstance.getSeat() !=null ? seatInstance.getSeat().getSeatNumber() : null)
                .seatType(seatInstance.getSeat() != null ? seatInstance.getSeat().getSeatType().name() : null)
                .seatPosition(seatInstance.getSeat() != null ? seatInstance.getSeat().getFullPosition() : null)
                .seat(SeatMapper.toResponse(seatInstance.getSeat()))
                .status(seatInstance.getStatus())
                .flightInstanceId(seatInstance.getFlightInstanceId())
                .flightCabinId(seatInstance.getFlightInstanceCabin() != null ? seatInstance.getFlightInstanceCabin().getId() : null)
                .fare(seatInstance.getFare())
                .price(seatInstance.getPremiumSuperCharge())
                .version(seatInstance.getVersion())
                .createdAt(seatInstance.getCreatedAt())
                .updatedAt(seatInstance.getUpdatedAt())
                .isAvailable(seatInstance.isAvailable())
                .isBooked(seatInstance.isBooked())
                .isOccupied(seatInstance.getStatus() == SeatAvailabilityStatus.OCCUPIED)
//                .seatCharacteristics(
//                           seatInstance.getSeat() != null ? seatInstance.getCharacterstics() : null)
                .build();
    }
}
