package com.microservice.airline.pricing_service.model;

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
public class FareRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    private Long airlineId;

    @OneToOne
    private Fare fare;

    private Boolean isRefundable;

    private Double changeFee;

    private  Double cancellationFee;

    private Integer refundDeadlineDays;

    private Integer changeDeadlineHours;

    private Boolean isChangeable;

    @CreationTimestamp
    private Instant createAt;

    @UpdateTimestamp
    private Instant updateAt;

}
