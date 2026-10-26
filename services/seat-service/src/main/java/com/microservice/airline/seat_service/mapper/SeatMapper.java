package com.microservice.airline.seat_service.mapper;

import com.microservice.airline.payload.response.SeatResponse;
import com.microservice.airline.seat_service.model.Seat;

public class SeatMapper {

    public static SeatResponse toResponse(Seat seat){

        return SeatResponse.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .seatRow(seat.getSeatRow())
                .columnLetter(seat.getColumnLetter())
                .seatType(seat.getSeatType())
                .isAvailable(seat.getIsAvailable())
                .isBlocked(seat.getIsBlocked())
                .isEmergencyExit(seat.getIsEmergencyExit())
                .isActive(seat.getIsActive())
                .basePrice(seat.getBasePrice())
                .premiumSuperCharge(seat.getPremiumSuperCharge())
                .totalPrice(seat.getTotalPrice())
                .hasExtraLegroom(seat.getHasExtraLegroom())
                .hasPowerOutlet(seat.getHasPowerOutlet())
                .hasTvScreen(seat.getHasTvScreen())
                .hasExtraWidth(seat.getHasExtraWidth())
                .seatPitch(seat.getSeatPitch())
                .seatWidth(seat.getSeatWidth())
                .reclineAngle(seat.getReclineAngle())
                .seatMapId(seat.getSeatMap() != null ? seat.getSeatMap().getId() : null)
                .seatMapName(seat.getSeatMap() != null ? seat.getSeatMap().getName() : null)
                .cabinClassId(seat.getSeatMap() != null && seat.getSeatMap().getCabinClass() != null ? seat.getSeatMap().getCabinClass().getId() : null)
                .cabinClassName(seat.getSeatMap() != null && seat.getSeatMap().getCabinClass() != null ? seat.getSeatMap().getCabinClass().getName().toString() : null)
                .createdAt(seat.getCreatedAt())
                .updatedAt(seat.getUpdatedAt())
                .createdBy(seat.getCreatedBy())
                .updatedBy(seat.getUpdatedBy())
                .isBookable(seat.isBookable())
                .fullPosition(seat.getFullPosition())
                .build();
    }
}
