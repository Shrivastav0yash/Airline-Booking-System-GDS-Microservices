package com.microservice.airline.payload.response;

import com.microservice.airline.enums.SeatType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {

    private Long id;
    private String seatNumber;
    private Integer seatRow;
    private Character columnLetter;
    private SeatType seatType;

    private Boolean isAvailable;
    private Boolean isBlocked;
    private Boolean isEmergencyExit;
    private Boolean isActive;

    private Double basePrice;
    private Double premiumSuperCharge;
    private Double totalPrice;

    private Boolean hasExtraLegroom;
    private Boolean hasTvScreen;
    private Boolean hasPowerOutlet;

    private Boolean hasExtraWidth;

    private Integer seatPitch;
    private Integer seatWidth;
    private Integer reclineAngle;

    private Long seatMapId;
    private String seatMapName;
    private Long cabinClassId;
    private String cabinClassName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    private Boolean isPremiumSeat;
    private Boolean isBookable;
    private String fullPosition;
    private String seatCharacteristics;

}
