package com.microservice.airline.payload.request;

import com.microservice.airline.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequest {

    @NotBlank(message = "Seat number is required")
    private String seatNumber;

    @NotNull(message = "Seat row is required")
    private Integer seatRow;

    private Character columnLetter;

    @NotNull(message = "Seat type is required")
    private SeatType seatType;

    @NotNull(message = "Base price is required")
    private Long seatMapId;

    private Long cabinClassId;

    private Boolean isAvailable;
    private Boolean isBlocked;
    private Boolean isEmergencyExit;
    private Boolean isActive;

    private Double basePrice;
    private Double premiumSuperCharge;

    private Boolean hasExtraLegroom;

    private Boolean hasTvScreen;
    private Boolean hasPowerOutlet;

    private Boolean hasExtraWidth;

    private Integer seatPitch;
    private Integer seatWidth;
    private Integer reclineAngle;


}
