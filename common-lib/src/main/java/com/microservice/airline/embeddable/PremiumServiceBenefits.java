package com.microservice.airline.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PremiumServiceBenefits {

    @Column(name = "priority_boarding", nullable = false)
    @Builder.Default
    private Boolean loungeAccess = false;

    @Column(name = "airport_transfer", nullable = false)
    @Builder.Default
    private Boolean airportTransfer = false;
}
