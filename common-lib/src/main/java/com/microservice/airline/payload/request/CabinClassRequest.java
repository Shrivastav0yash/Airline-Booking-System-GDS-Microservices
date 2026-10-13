package com.microservice.airline.payload.request;

import com.microservice.airline.enums.CabinClassType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CabinClassRequest {

    @NotBlank(message = "Cabin class name is required")
    private CabinClassType name;

    @NotBlank(message = "Cabin class code is required")
    private String code;

    private String description;

    @NotNull(message = "Aircraft ID is required")
    private Long aircraftId;

    private Integer displayOrder;
    private Boolean isActive;
    private Boolean isBookable;

    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;

    private Integer seatType;

}
