package com.microservice.airline.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaggagePolicyResponse {
    private Long id;
    private String name;
    private String description;
    private Double weightLimit;
    private Double dimensionLimit;
    private Double feePerKgOverweight;
    private Double feePerKgOversize;
}
