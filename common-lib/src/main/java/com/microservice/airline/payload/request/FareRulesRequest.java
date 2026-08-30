package com.microservice.airline.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FareRulesRequest {

    @NotBlank(message = "Rule name is required")
    private String ruleName;

    @NotNull(message = "Fare ID is required")
    private Long fareId;

    private Long airlineId;

    private Boolean isRefundable;

    @PositiveOrZero(message = "change fee must be positive or zero")
    private Double changeFee;

    @PositiveOrZero(message = "cancellation fee must be positive or zero")
    private Double cancellationFee;

    @PositiveOrZero(message = "refund deadline days  must be positive or zero")
    private Integer refundDeadlineDays;

    @PositiveOrZero(message = "change deadline hours must be positive or zero")
    private Integer changeDeadlineHours;

    private Boolean isChangeable;



}
