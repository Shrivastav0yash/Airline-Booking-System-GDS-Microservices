package com.microservice.airline.pricing_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaggagePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private Fare fare;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private Double cabinBaggageMaxWeight;

    private Integer cabinBaggagePieces =1;

    private Integer cabinBaggageWeightPerPiece;

    private Integer cabinBaggageMaxDimensions; // in cm, sum of length + width + height

    private Double checkInBaggageMaxWeight;

    private Integer checkInBaggagePieces =1;

    private Double checkInBaggageWeightPerPiece;

    private Integer freeCheckedBagsAllowance = 0;

    private Boolean priorityBaggage = false;

    private Boolean extraBaggageAllowance = false;

    private Long airlineId;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

}
